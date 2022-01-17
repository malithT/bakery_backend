package com.bakery.bakeryProducts.service.impl;

import com.bakery.bakeryProducts.entity.OrderHeader;
import com.bakery.bakeryProducts.repository.OrderHeaderRepository;
import com.bakery.bakeryProducts.service.OrderHeaderService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderHeaderServiceImpl implements OrderHeaderService {

    private final OrderHeaderRepository orderHeaderRepository;

    @Override
    public List<OrderHeader> getAllHeaderOrders() {
        try{
            orderHeaderRepository.findAll();
        }catch(Exception e){
            e.printStackTrace();
        }
        return orderHeaderRepository.findAll();
    }

    @Override
    public String editHeaderOrder(OrderHeader orderHeader) {
        JSONObject alert = new JSONObject();
        orderHeaderRepository.editHeaderOrder(orderHeader.getOrderHeaderId(), orderHeader.getCustomerMobile(),
                                                orderHeader.getCustomerName(), orderHeader.getDeliveryDate(),
                                                orderHeader.getOrderStatus());
        alert.put("message","Order Updated Successfully");
        return alert.toString() ;
    }

    @Override
    public Optional<OrderHeader> findOrderById(int orderId) {
        return orderHeaderRepository.findById(orderId);
    }
}
