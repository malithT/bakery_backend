package com.bakery.bakeryProducts.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "order_header_details")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class OrderHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_header_id")
    int orderHeaderId;
    String customerName;
    String customerMobile;
    @Temporal(TemporalType.DATE)
    Date deliveryDate;
    String orderStatus;
    @Temporal(TemporalType.DATE)
    Date addedDate;
    String createdBy;
}
