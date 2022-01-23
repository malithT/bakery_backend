package com.bakery.bakeryProducts.dto;

import com.bakery.bakeryProducts.entity.OrderDetail;
import com.bakery.bakeryProducts.entity.OrderHeader;
import lombok.Data;

import java.util.List;

@Data
public class CustomOrderDetails {
    OrderHeader orderHeader;
    List<CustomOrderResponse> orderDetail;
}
