package com.bakery.bakeryProducts.service;

import com.bakery.bakeryProducts.entity.Product;
import com.bakery.bakeryProducts.entity.ProductCategory;

import java.util.List;

public interface ProductService {

    String saveProduct(Product product);
    List<Product> getAllProducts();
    String editProduct(int productId, String productName ,Double productPrice,int productCategoryId);
    String deleteProduct(int productId);
}