package com.doger.order.mapper;

import com.doger.order.entity.ProductOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductOrder record);

    int insertSelective(ProductOrder record);

    ProductOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductOrder record);

    int updateByPrimaryKey(ProductOrder record);

    List<ProductOrder> selectAll();

    ProductOrder selectByOrderNo(@Param("orderNo") String orderNo);
}