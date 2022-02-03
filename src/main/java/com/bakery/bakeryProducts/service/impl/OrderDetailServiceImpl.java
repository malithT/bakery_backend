package com.bakery.bakeryProducts.service.impl;

import com.bakery.bakeryProducts.dto.CustomOrderDetails;
import com.bakery.bakeryProducts.dto.CustomOrderResponse;
import com.bakery.bakeryProducts.entity.OrderDetail;
import com.bakery.bakeryProducts.entity.OrderHeader;
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
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public String saveOrder(CustomOrderDetails customOrderDetails) {
        JSONObject alert = new JSONObject();
        Double totAmount = 0.0;
        String month;
        if(customOrderDetails.getOrderHeader().getMonth().length() == 1){
           month = "0" + customOrderDetails.getOrderHeader().getMonth();
        }else{
            month = customOrderDetails.getOrderHeader().getMonth();
        }
        customOrderDetails.getOrderHeader().setMonth(month);
        orderHeaderRepository.save(customOrderDetails.getOrderHeader());
        for (CustomOrderResponse orderDetails : customOrderDetails.getOrderDetail()) {

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(productRepository.getById(orderDetails.getProductId()));
            orderDetail.setProductCategory(productCategoryRepository.getById(orderDetails.getProductCategoryId()));
            orderDetail.setOrderHeader(orderHeaderRepository.findFirstByOrderByOrderHeaderIdDesc());
            orderDetail.setAmount(orderDetails.getAmount());
            orderDetail.setQuantity(orderDetails.getQuantity());
            orderDetailRepository.save(orderDetail);
            totAmount = orderDetail.getAmount() + totAmount;
        }
        OrderHeader orderHeader = orderHeaderRepository.findFirstByOrderByOrderHeaderIdDesc();
        orderHeaderRepository.updateHeader(totAmount,orderHeader.getOrderHeaderId());
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
