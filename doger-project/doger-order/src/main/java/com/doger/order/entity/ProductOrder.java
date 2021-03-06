package com.doger.order.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ProductOrder {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 实付金额
     */
    private Long amount;

    /**
     * 交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date transTime;

    /**
     * 产品编号
     */
    private String productNo;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品价格
     */
    private Long productPrice;

    /**
     * 产品数量
     */
    private Long productCount;

    /**
     * 折扣
     */
    private Long discount;

    /**
     * 顾客编号
     */
    private String consumerNo;

    /**
     * 原始金额
     */
    private Long originAmount;
}