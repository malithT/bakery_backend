package com.bakery.bakeryProducts.service.impl;

import com.bakery.bakeryProducts.entity.Product;
import com.bakery.bakeryProducts.repository.ProductCategoryRepository;
import com.bakery.bakeryProducts.repository.ProductRepository;
import com.bakery.bakeryProducts.service.ProductCategoryService;
import com.bakery.bakeryProducts.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public String saveProduct(Product product) {
        productRepository.save(product);
        JSONObject alert = new JSONObject();
        alert.put("message","Product Added Successfully");
        return alert.toString();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public String editProduct(int productId, String productName, Double productPrice, int productCategoryId) {

        JSONObject alert = new JSONObject();
        productRepository.editProduct(productId,productName,productPrice,productCategoryId);
        alert.put("message","Product Updated Successfully");
        return alert.toString();
    }

    @Override
    public String deleteProduct(int productId) {

        Product product = productRepository.getById(productId);
        productRepository.delete(product);
        JSONObject alert = new JSONObject();
        alert.put("message","Product Deleted Successfully");
        return alert.toString();
    }
}