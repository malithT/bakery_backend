package com.bakery.bakeryProducts.service;

import com.bakery.bakeryProducts.dto.CustomOrderDetails;
import com.bakery.bakeryProducts.entity.OrderDetail;
import com.bakery.bakeryProducts.entity.ProductCategory;
import org.hibernate.criterion.Order;

import java.util.List;

public interface OrderDetailService {

    String saveOrder(CustomOrderDetails customOrderDetails);
    List<OrderDetail> getAllOrders();
    String editOrder(OrderDetail orderDetail);
    List<OrderDetail> getDetailByOrderHeader(int orderHeaderId);
    OrderDetail getDetailByOrderDetailId(int orderDetailId);

}
