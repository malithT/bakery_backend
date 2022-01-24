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

    @Query(nativeQuery = true,value = "select customer_name as customerName from customer_details")
    List<Map<String, String>> getAllCustomer();

    CustomerDetails findCustomerDetailsByCustomerName(String customerName);
}
