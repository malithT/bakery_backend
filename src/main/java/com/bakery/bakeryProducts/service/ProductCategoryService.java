package com.bakery.bakeryProducts.service;

import com.bakery.bakeryProducts.entity.ProductCategory;
import com.bakery.bakeryProducts.entity.User;

import java.util.List;
import java.util.Map;

public interface ProductCategoryService {
    String saveProductCategory(ProductCategory productCategory);
    List<ProductCategory> getAllProductCategories();
    String editProductCategory(int productCategoryId, String productCategoryName);
    String deleteProductCategory(int productCategoryId);
}