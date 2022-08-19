package com.doger.nacosorder.service.impl;

import com.doger.nacosorder.dao.OrderMapper;
import com.doger.nacosorder.dto.APIResponse;
import com.doger.nacosorder.entity.Account;
import com.doger.nacosorder.entity.Order;
import com.doger.nacosorder.entity.Storage;
import com.doger.nacosorder.service.AccountService;
import com.doger.nacosorder.service.OrderService;
import com.doger.nacosorder.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;



@Log4j2
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private AccountService accountService;

    @Resource
    private StorageService storageService;

    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    @Override
    public APIResponse createOrder(Order order) {
         if(StringUtils.isEmpty(order.getUserId())){
             return APIResponse.fail("缺少用户参数");
         }
        if(StringUtils.isEmpty(order.getCommodityCode())){
            return APIResponse.fail("缺少产品参数");
        }
        if(order.getMoney()==null){
            return APIResponse.fail("缺少金额参数");
        }
        if(order.getMoney()<0){
            return APIResponse.fail("金额非法");
        }
        if(order.getCount()==null){
            return APIResponse.fail("缺少数量参数");
        }
        if(order.getCount()<0){
            return APIResponse.fail("数量非法");
        }

        orderMapper.insert(order);

        Account account=new Account();
        account.setUserId(order.getUserId());
        account.setMoney(order.getMoney());
        APIResponse apiResponse=accountService.add(account);
        if(!"000000".equals(apiResponse.getCode())){
            throw new IllegalArgumentException("userId");
        }
        Storage storage=new Storage();
        storage.setCommodityCode(order.getCommodityCode());
        storage.setCount(order.getCount());
        apiResponse=storageService.sub(storage);
        if(!"000000".equals(apiResponse.getCode())){
            throw new IllegalArgumentException("commodityCode");
        }
        return APIResponse.ok(order);
    }
}
