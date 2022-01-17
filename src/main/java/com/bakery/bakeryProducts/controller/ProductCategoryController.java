package com.bakery.bakeryProducts.controller;

import com.bakery.bakeryProducts.entity.Product;
import com.bakery.bakeryProducts.entity.ProductCategory;
import com.bakery.bakeryProducts.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productCategoryDetails")
@AllArgsConstructor
public class ProductCategoryController {

    ProductCategoryService productCategoryService;

    @PostMapping("/saveProductCategory")
    public String saveProductCategory(@RequestBody ProductCategory productCategory){

        return  productCategoryService.saveProductCategory(productCategory);
    }

    @GetMapping("/getAllProductCategories")
    public List<ProductCategory> getAllProductCategories(){

        return  productCategoryService.getAllProductCategories();
    }

    @PostMapping("/editProductCategory")
    public String editProductCategory(@RequestBody ProductCategory productCategory){

        return  productCategoryService.editProductCategory(productCategory.getProductCategoryId(), productCategory.getCategoryName());
    }

    @PostMapping("/deleteProductCategory")
    public String deleteProductCategory(@RequestParam ("productCategoryId") int productCategoryId){

        return  productCategoryService.deleteProductCategory(productCategoryId);
    }

    @PostMapping("/searchByProductCategoryId")
    public Optional<ProductCategory> searchUserByProductCategoryId(@RequestParam("productCategoryId") int productCategoryId){

        return  productCategoryService.searchUserByProductCategoryId(productCategoryId);
    }
}
