package com.doger.nacosorder.service;

import com.doger.nacosorder.dto.APIResponse;
import com.doger.nacosorder.entity.Order;

public interface OrderService {

    APIResponse createOrder(Order order);
}
