package com.bakery.bakeryProducts.service;

import com.bakery.bakeryProducts.entity.CustomerDetails;
import com.bakery.bakeryProducts.entity.OrderHeader;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderHeaderService {
    List<OrderHeader> getAllHeaderOrders();
    String editHeaderOrder(OrderHeader orderHeader);
    Optional<OrderHeader> findOrderById(int orderId);
}
