package com.bakery.bakeryProducts.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "customer_details")
public class CustomerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int customerId;
    String customerName;
    String customerMobile;

}
