package com.bakery.bakeryProducts.repository;


import com.bakery.bakeryProducts.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value ="UPDATE `product_category` " +
            "SET category_name=:productCategoryName where product_category_id=:productCategoryId")
    int editProductCategory (int productCategoryId,String productCategoryName);
}
