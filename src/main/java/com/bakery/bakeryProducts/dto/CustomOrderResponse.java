package com.bakery.bakeryProducts.dto;

import com.bakery.bakeryProducts.entity.OrderHeader;
import lombok.Data;

import javax.persistence.OneToMany;

@Data
public class CustomOrderResponse {
    int orderDetailId;
    int productCategoryId;
    int productId;
    OrderHeader orderHeader;
    int quantity;
    Double amount;
}
