package com.bakery.bakeryProducts.repository;

import com.bakery.bakeryProducts.entity.OrderDetail;
import com.bakery.bakeryProducts.entity.OrderHeader;
import com.bakery.bakeryProducts.entity.Product;
import com.bakery.bakeryProducts.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value ="UPDATE `order_details` " +
            "SET product_category_id=:productCategory," +
            "product_id = :product," +
            "quantity = :quantity," +
            "amount =:amount " +
            "where order_detail_id=:orderDetailId")
    int editOrder(int orderDetailId, ProductCategory productCategory, Product product,Integer quantity,Double amount);

    List<OrderDetail> findOrderDetailByOrderHeader(OrderHeader orderHeader);
    OrderDetail findOrderDetailByOrderDetailId(int orderDetailId);

    @Query(nativeQuery = true,value = "select SUM(amount) as totAmount from order_details where order_header_id =:orderHeaderId")
    Double updateTotAmount(int orderHeaderId);

}
