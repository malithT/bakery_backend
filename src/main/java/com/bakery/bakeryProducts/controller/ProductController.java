package com.bakery.bakeryProducts.controller;

import com.bakery.bakeryProducts.entity.Product;
import com.bakery.bakeryProducts.entity.ProductCategory;
import com.bakery.bakeryProducts.entity.User;
import com.bakery.bakeryProducts.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productDetails")
@AllArgsConstructor
public class ProductController {

    ProductService productService;

    @PostMapping("/saveProduct")
    public String saveProduct(@RequestBody Product product){
        return  productService.saveProduct(product);
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts(){

        return  productService.getAllProducts();
    }

    @PostMapping("/editProduct")
    public String editProducts(@RequestBody Product product){

        return  productService.editProduct(product.getProductId(), product.getProductName(),product.getProductPrice(),product.getProductCategory().getProductCategoryId());
    }

    @PostMapping("/deleteProduct")
    public String deleteProductCategory(@RequestParam ("productId") int productId){

        return  productService.deleteProduct(productId);
    }

    @PostMapping("/searchByProductId")
    public Optional<Product> searchUserByProductId(@RequestParam("productId") int productId){

        return  productService.searchUserByProductId(productId);
    }
}
