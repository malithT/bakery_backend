package com.bakery.bakeryProducts.repository;

import com.bakery.bakeryProducts.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetails,Integer> {

    CustomerDetails findCustomerDetailsByCustomerName(String customerName);
}
