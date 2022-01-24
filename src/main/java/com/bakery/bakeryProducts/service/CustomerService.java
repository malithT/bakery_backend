package com.bakery.bakeryProducts.service;

import com.bakery.bakeryProducts.entity.CustomerDetails;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerService {
    String saveCustomer(CustomerDetails customerDetails);
    List<CustomerDetails> getAllCustomers();
    CustomerDetails getCustomerByName(String customerName);
}
