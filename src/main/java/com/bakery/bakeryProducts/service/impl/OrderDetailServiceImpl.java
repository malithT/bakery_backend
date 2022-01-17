package com.bakery.bakeryProducts.service.impl;

import com.bakery.bakeryProducts.entity.OrderDetail;
import com.bakery.bakeryProducts.repository.OrderDetailRepository;
import com.bakery.bakeryProducts.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Override
    public String saveOrder(OrderDetail orderDetail) {
        JSONObject alert = new JSONObject();
        orderDetailRepository.save(orderDetail);
        alert.put("message","Order Created Successfully");
        return alert.toString();
    }

    @Override
    public List<OrderDetail> getAllOrders() {
        return orderDetailRepository.findAll();
    }

    @Override
    public String editOrder(OrderDetail orderDetail) {
        JSONObject alert = new JSONObject();
        orderDetailRepository.editOrder(orderDetail.getOrderDetailId(),orderDetail.getProductCategory(),
                                    orderDetail.getProduct(), orderDetail.getQuantity());
        return alert.toString();
    }
}
