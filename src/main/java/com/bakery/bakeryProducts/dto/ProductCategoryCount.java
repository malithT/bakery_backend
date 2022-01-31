package com.bakery.bakeryProducts.dto;

import lombok.Data;
import java.util.Collection;

@Data
public class ProductCategoryCount {
    Collection<String> productCategory;
    Collection<Long> productCount;
}
