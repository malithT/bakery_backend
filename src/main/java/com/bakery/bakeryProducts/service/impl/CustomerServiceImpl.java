package com.bakery.bakeryProducts.service.impl;

import com.bakery.bakeryProducts.entity.CustomerDetails;
import com.bakery.bakeryProducts.repository.CustomerRepository;
import com.bakery.bakeryProducts.service.CustomerService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    public final CustomerRepository customerRepository;

    @Override
    public String saveCustomer(CustomerDetails customerDetails) {
        JSONObject alert = new JSONObject();
        customerRepository.save(customerDetails);
        alert.put("message","Customer Created Successfully");
        return alert.toString();
    }

    @Override
    public List<CustomerDetails> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerDetails getCustomerByName(String customerName) {
        return customerRepository.findCustomerDetailsByCustomerName(customerName);
    }
}
