package com.bakery.bakeryProducts.service;

import com.bakery.bakeryProducts.entity.OrderDetail;
import java.util.List;

public interface OrderDetailService {

    String saveOrder(OrderDetail orderDetail);
    List<OrderDetail> getAllOrders();
    String editOrder(OrderDetail orderDetail);
}
