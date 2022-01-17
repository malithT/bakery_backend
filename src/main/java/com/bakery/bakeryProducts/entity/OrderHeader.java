package com.bakery.bakeryProducts.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "order_header_details")
public class OrderHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_header_id")
    int orderHeaderId;
    String customerName;
    String customerMobile;
    Date deliveryDate;
    String orderStatus;
    Date addedDate;
}
