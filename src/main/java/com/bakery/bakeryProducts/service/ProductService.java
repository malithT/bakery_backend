package com.bakery.bakeryProducts.service;
import com.bakery.bakeryProducts.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    String saveProduct(Product product);
    List<Product> getAllProducts();
    String editProduct(int productId, String productName ,Double productPrice,int productCategoryId);
    String deleteProduct(int productId);
    Optional<Product> searchUserByProductId(int productId);
    List<Product> getSelectedProducts(int productCategoryId);
    Product getProductPrice(int productId);
}
