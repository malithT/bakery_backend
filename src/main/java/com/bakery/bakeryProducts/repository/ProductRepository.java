package com.bakery.bakeryProducts.repository;

import com.bakery.bakeryProducts.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value ="UPDATE `product` " +
            "SET product_name=:productName,product_price =:productPrice,product_category_id =:productCategoryId where product_id=:productId")
    int editProduct(int productId, String productName ,Double productPrice,int productCategoryId);
}
