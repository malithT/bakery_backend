package com.bakery.bakeryProducts.repository;

import com.bakery.bakeryProducts.entity.CustomerDetails;
import com.bakery.bakeryProducts.service.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetails,Integer> {

    CustomerDetails findCustomerDetailsByCustomerName(String customerName);
}
