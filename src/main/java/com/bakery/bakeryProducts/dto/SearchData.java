package com.bakery.bakeryProducts.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SearchData {
    Date dateTo;
    Date dateFrom;
    String customerName;
    String productCategoryName;
    String productName;
    Integer productCategoryId;
    Integer productId;
}
