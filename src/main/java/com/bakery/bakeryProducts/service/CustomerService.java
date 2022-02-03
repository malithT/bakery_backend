package com.bakery.bakeryProducts.service;

import com.bakery.bakeryProducts.entity.CustomerDetails;

import java.util.List;

public interface CustomerService {
    String saveCustomer(CustomerDetails customerDetails);
    List<CustomerDetails> getAllCustomers();
    CustomerDetails getCustomerByName(String customerName);
}
