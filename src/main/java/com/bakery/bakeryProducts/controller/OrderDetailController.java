package com.bakery.bakeryProducts.controller;

import com.bakery.bakeryProducts.dto.CustomOrderDetails;
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
    public String saveOrder(@RequestBody CustomOrderDetails customOrderDetails){
        return  orderDetailService.saveOrder(customOrderDetails);
    }

    @GetMapping("/getAllOrders")
    public List<OrderDetail> getAllOrders(){

        return  orderDetailService.getAllOrders();
    }

    @PostMapping("/editOrder")
    public String editProducts(@RequestBody OrderDetail orderDetail){

        return  orderDetailService.editOrder(orderDetail);
    }

    @PostMapping("/getDetailByOrderHeader")
    public List<OrderDetail> getDetailByOrderHeader(@RequestParam ("orderHeaderId") int orderHeaderId){

        return  orderDetailService.getDetailByOrderHeader(orderHeaderId);
    }


    @PostMapping("/getDetailsById")
    public OrderDetail getDetailsById(@RequestParam ("orderDetailId") int orderDetailId){

        return  orderDetailService.getDetailByOrderDetailId(orderDetailId);
    }



}
