package com.doger.mq.config;

import com.rabbitmq.client.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
@Configuration
public class RabbitConfig {

    public String getSendQueue() {
        return sendQueue;
    }

    @Value("${rabbit.sendQueue:doger-mq.test}")
    private String sendQueue;

    public String getBroadcastExchange() {
        return broadcastExchange;
    }

    @Value("${rabbit.broadcastExchange:test-broadcast}")
    private String broadcastExchange;

    public String getRouteExchange() {
        return routeExchange;
    }

    @Value("${rabbit.broadcastExchange:test-route}")
    private String routeExchange;

    public String getTopicExchange() {
        return topicExchange;
    }


    @Value("${rabbit.broadcastExchange:test-topic}")
    private String topicExchange;

    public String getHeaderExchange() {
        return headerExchange;
    }

    @Value("${rabbit.broadcastExchange:test-header}")
    private String headerExchange;

    public String getHeaderRouteKey() {
        return headerRouteKey;
    }

    @Value("${rabbit.broadcastExchange:test-header-key}")
    private String headerRouteKey;

    public String getRpcKey() {
        return rpcKey;
    }

    @Value("${rabbit.broadcastExchange:test-rpc-key}")
    private String rpcKey;


    public String[] getRouteKeys() {
        return routeKeys;
    }

    private final String[] routeKeys=new String[]{"Black","White","Gray"};

    private static final Object lock=new Object();

    @Resource
    private ConnectionFactory rabbitFactory;



    private void initRec(){
        try (Connection connection = rabbitFactory.newConnection();
             Channel channel = connection.createChannel()) {

            initHelloConsume(channel);
            initBroadcastConsume(channel);
            initRouteConsume(channel);
            initTopicConsume(channel);
            initHeaderConsume(channel);
            initRpcConsume(channel);
            log.info("rec block...........");
            synchronized (lock){
                lock.wait();
            }
            log.info("rec release...........");
        } catch (Exception ex) {
            log.error("接收异常", ex);
        }
    }


    private void initHelloConsume(Channel channel) throws IOException{
        channel.queueDeclare(getSendQueue(), false, false, false, null);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
            log.info("rec message:" + msg);
        };
        channel.basicConsume(getSendQueue(), true, deliverCallback, consumerTag -> {
        });

    }

    private void initBroadcastConsume(Channel channel) throws IOException {
            channel.exchangeDeclare(getBroadcastExchange(), BuiltinExchangeType.FANOUT);
            String queueName=channel.queueDeclare().getQueue();
            channel.queueBind(queueName, getBroadcastExchange(),"");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
                log.info("rec broadcast message:" + msg);
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
    }


    private void initRouteConsume(Channel channel) throws IOException {
            channel.exchangeDeclare(getRouteExchange(), BuiltinExchangeType.DIRECT);
            //route key=Black
            String queueBlackName=channel.queueDeclare().getQueue();
            channel.queueBind(queueBlackName, getRouteExchange(),getRouteKeys()[0]);
            DeliverCallback deliverBlackCallback = (consumerTag, delivery) -> {
                String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
                log.info("rec route[black] message:" + msg);
            };
            channel.basicConsume(queueBlackName, true, deliverBlackCallback, consumerTag -> {
            });

            //route key=White,Gray
            String queueWhiteName=channel.queueDeclare().getQueue();
            channel.queueBind(queueWhiteName, getRouteExchange(),getRouteKeys()[1]);
            channel.queueBind(queueWhiteName, getRouteExchange(),getRouteKeys()[2]);
            DeliverCallback deliverWhiteCallback = (consumerTag, delivery) -> {
                String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
                log.info("rec route[white] message:" + msg);
            };
            channel.basicConsume(queueWhiteName, true, deliverWhiteCallback, consumerTag -> {
            });
    }

    private void initTopicConsume(Channel channel) throws IOException {
        channel.exchangeDeclare(getTopicExchange(), BuiltinExchangeType.TOPIC);
        for ( int i=0;i<getRouteKeys().length;i++){
            String key=getRouteKeys()[i];
            String topicKey=key+".*";
            String queueName=channel.queueDeclare().getQueue();
            channel.queueBind(queueName, getTopicExchange(),topicKey);
            DeliverCallback callback = (consumerTag, delivery) -> {
                String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
                log.info(String.format("rec route[topicKey=%s] message:%s",topicKey,msg));
            };
            channel.basicConsume(queueName, true, callback, consumerTag -> {
            });

        }
    }


    private void initHeaderConsume(Channel channel) throws IOException {
        channel.exchangeDeclare(getHeaderExchange(), BuiltinExchangeType.HEADERS);
        String routeKey=getHeaderRouteKey();
        String queueName=channel.queueDeclare().getQueue();
        Map<String,Object> header=new HashMap<>();
        header.put("sex","M");
        header.put("age",32);
        channel.queueBind(queueName, getHeaderExchange(),routeKey,header);
        DeliverCallback callback = (consumerTag, delivery) -> {
            String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
            log.info(String.format("rec route[header=%s] message:%s",routeKey,msg));
        };
        channel.basicConsume(queueName, true, callback, consumerTag -> {
        });

    }

    private void initRpcConsume(Channel channel) throws IOException {
        String queueName=getRpcKey();
        channel.queueDeclare(queueName,false, false, false, null);

        DeliverCallback callback = (consumerTag, delivery) -> {
            String correlationId=delivery.getProperties().getCorrelationId();
            String replyQueue=delivery.getProperties().getReplyTo();
            AMQP.BasicProperties basicProperties=new AMQP.BasicProperties().builder()
                    .correlationId(correlationId)
                    .build();
            String numStr=new String(delivery.getBody(),StandardCharsets.UTF_8);
            Integer num=Integer.parseInt(numStr);
            String response="";
            log.info("start calc.............");
            if(num<0){
                response+=0;
            }else{
                response+=fib(num);
            }
            log.info("end calc.............");
            channel.basicPublish("",replyQueue,basicProperties,response.getBytes(StandardCharsets.UTF_8));
        };
        channel.basicConsume(queueName, true, callback, consumerTag -> {
        });

    }

    private static int fib(int n){
        if(n==1) {
            return 1;
        }
        if(n==2){
            return 1;
        }
        return fib(n-1)+fib(n-2);

    }

    @PostConstruct
    private void init() {
        log.info("init rabbitConfig............");
        ExecutorService executorService= Executors.newFixedThreadPool(4);
        executorService.execute(this::initRec);
        log.info("start rabbitConfig............");
    }

    @PreDestroy
    private void destroy(){
        log.info("destroy rabbitConfig...........");
        synchronized (lock) {
            lock.notifyAll();
        }

        log.info("destroy rabbitConfig complete...........");
    }
//
//    public static void main(String[] args) {
//        int i=45;
//        System.out.println(String.format("fib(%d)=%d",i,fib(i)));
//    }


}
