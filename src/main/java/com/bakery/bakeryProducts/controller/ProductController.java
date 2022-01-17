package com.bakery.bakeryProducts.controller;

import com.bakery.bakeryProducts.entity.Product;
import com.bakery.bakeryProducts.entity.ProductCategory;
import com.bakery.bakeryProducts.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productDetails")
@AllArgsConstructor
public class ProductController {

    ProductService productService;

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute Product product){
        return  productService.saveProduct(product);
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts(){

        return  productService.getAllProducts();
    }

    @PostMapping("/editProduct")
    public String editProducts(@ModelAttribute Product product){

        return  productService.editProduct(product.getProductId(), product.getProductName(),product.getProductPrice(),product.getProductCategory().getProductCategoryId());
    }

    @PostMapping("/deleteProduct")
    public String deleteProductCategory(@RequestParam ("productId") int productId){

        return  productService.deleteProduct(productId);
    }
}
