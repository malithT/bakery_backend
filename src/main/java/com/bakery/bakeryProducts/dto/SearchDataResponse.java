package com.bakery.bakeryProducts.dto;

import com.bakery.bakeryProducts.entity.OrderDetail;
import lombok.Data;

import java.util.List;

@Data
public class SearchDataResponse {
    List<OrderDetail> searchData;
    StatusCount statusCount;
    ProductCategoryCount productCategoryCount;
}
