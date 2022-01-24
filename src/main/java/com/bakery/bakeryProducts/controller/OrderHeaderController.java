package com.bakery.bakeryProducts.controller;

import com.bakery.bakeryProducts.entity.CustomerDetails;
import com.bakery.bakeryProducts.entity.OrderHeader;
import com.bakery.bakeryProducts.service.OrderHeaderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/orderHeaderDetails")
@AllArgsConstructor
public class OrderHeaderController {

    OrderHeaderService orderHeaderService;


    @GetMapping("/getAllHeaderOrders")
    public List<OrderHeader> getAllHeaderOrders(){

        return  orderHeaderService.getAllHeaderOrders();
    }

    @PostMapping("/editHeaderOrder")
    public String editHeaderOrder(@RequestBody OrderHeader orderHeader){

        return  orderHeaderService.editHeaderOrder(orderHeader);
    }

    @PostMapping("/findOrderById")
    public Optional<OrderHeader> findOrderById(@RequestParam ("orderId") int orderId){

        return  orderHeaderService.findOrderById(orderId);
    }

}
