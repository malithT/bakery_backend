package com.bakery.bakeryProducts.service.impl;

import com.bakery.bakeryProducts.entity.OrderDetail;
import com.bakery.bakeryProducts.entity.OrderHeader;
import com.bakery.bakeryProducts.entity.Product;
import com.bakery.bakeryProducts.entity.ProductCategory;
import com.bakery.bakeryProducts.repository.OrderDetailRepository;
import com.bakery.bakeryProducts.repository.OrderHeaderRepository;
import com.bakery.bakeryProducts.repository.ProductCategoryRepository;
import com.bakery.bakeryProducts.repository.ProductRepository;
import com.bakery.bakeryProducts.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderHeaderRepository orderHeaderRepository;

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
                                    orderDetail.getProduct(), orderDetail.getQuantity(), orderDetail.getAmount());
        Double totAmount = orderDetailRepository.updateTotAmount(orderDetail.getOrderHeader().getOrderHeaderId());
        orderHeaderRepository.updateHeader(totAmount,orderDetail.getOrderHeader().getOrderHeaderId());
        System.out.println(totAmount);
        alert.put("message","Order Updated Successfully");
        return alert.toString();
    }

    @Override
    public List<OrderDetail> getDetailByOrderHeader(int orderHeaderId) {
        OrderHeader orderHeader = orderHeaderRepository.getById(orderHeaderId);
        return orderDetailRepository.findOrderDetailByOrderHeader(orderHeader);
    }

    @Override
    public OrderDetail getDetailByOrderDetailId(int orderDetailId) {
        return orderDetailRepository.findOrderDetailByOrderDetailId(orderDetailId);
    }

}
