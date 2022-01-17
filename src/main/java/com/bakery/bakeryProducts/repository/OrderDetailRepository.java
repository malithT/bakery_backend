package com.bakery.bakeryProducts.repository;

import com.bakery.bakeryProducts.entity.OrderDetail;
import com.bakery.bakeryProducts.entity.Product;
import com.bakery.bakeryProducts.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value ="UPDATE `order_detail` " +
            "SET product_category_id=:productCategory," +
            "product_id = :product," +
            "quantity = :quantity," +
            " where order_id=:orderDetailId")
    int editOrder(int orderDetailId, ProductCategory productCategory, Product product,Integer quantity);
}
