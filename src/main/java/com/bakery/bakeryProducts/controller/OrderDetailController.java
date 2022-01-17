package com.bakery.bakeryProducts.controller;

import com.bakery.bakeryProducts.entity.OrderDetail;
import com.bakery.bakeryProducts.service.OrderDetailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderDetails")
@AllArgsConstructor
public class OrderDetailController {

    OrderDetailService orderDetailService;

    @PostMapping("/saveOrder")
    public String saveOrder(@ModelAttribute OrderDetail orderDetail){
        return  orderDetailService.saveOrder(orderDetail);
    }

    @GetMapping("/getAllOrders")
    public List<OrderDetail> getAllOrders(){

        return  orderDetailService.getAllOrders();
    }

    @PostMapping("/editOrder")
    public String editProducts(@ModelAttribute OrderDetail orderDetail){

        return  orderDetailService.editOrder(orderDetail);
    }
}
