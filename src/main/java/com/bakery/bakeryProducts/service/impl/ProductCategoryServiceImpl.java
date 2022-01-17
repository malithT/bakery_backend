package com.bakery.bakeryProducts.service.impl;

import com.bakery.bakeryProducts.entity.ProductCategory;
import com.bakery.bakeryProducts.entity.User;
import com.bakery.bakeryProducts.repository.ProductCategoryRepository;
import com.bakery.bakeryProducts.service.ProductCategoryService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;


    @Override
    public String saveProductCategory(ProductCategory productCategory) {

        JSONObject alert = new JSONObject();
        productCategoryRepository.save(productCategory);
        alert.put("message","Product Category Added Successfully");
        return alert.toString();
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public String editProductCategory(int productCategoryId,String productCategoryName) {

        productCategoryRepository.editProductCategory(productCategoryId,productCategoryName);
        JSONObject alert = new JSONObject();
        alert.put("message","Product Category Updated Successfully");
        return alert.toString();
    }

    @Override
    public String deleteProductCategory(int productCategoryId) {
        ProductCategory productCategory = productCategoryRepository.getById(productCategoryId);
        productCategoryRepository.delete(productCategory);
        JSONObject alert = new JSONObject();
        alert.put("message","Product Category Deleted Successfully");
        return alert.toString();
    }
}
