package com.bakery.bakeryProducts.controller;


import com.bakery.bakeryProducts.entity.CustomerDetails;
import com.bakery.bakeryProducts.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerDetails")
@AllArgsConstructor
public class CustomerController {

    public final CustomerService customerService;

    @GetMapping("getAllCustomers")
    public List<CustomerDetails> getAllCustomers(){
        return  customerService.getAllCustomers();
    }

    @PostMapping("saveCustomer")
    public String saveCustomer(@RequestBody CustomerDetails customerDetails){
        return  customerService.saveCustomer(customerDetails);
    }

    @PostMapping("getCustomerByName")
    public CustomerDetails getCustomerById(@RequestParam("customerName") String customerName){
        return  customerService.getCustomerByName(customerName);
    }
}
