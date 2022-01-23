package com.bakery.bakeryProducts.repository;

import com.bakery.bakeryProducts.entity.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "UPDATE order_header_details " +
            "SET customer_mobile=:customerMobile," +
            "customer_name=:customerName," +
            "delivery_date=:deliveryDate," +
            "order_status=:orderStatus," +
            "year=:year," +
            "month=:month WHERE order_header_id=:orderId")
    int editHeaderOrder(int orderId , String customerMobile, String customerName, Date deliveryDate, String orderStatus,String year,String month);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "UPDATE order_header_details " +
            "SET tot_amount=:totAmount " +
            "WHERE order_header_id=:orderHeaderId")
    int updateHeader(Double totAmount,int orderHeaderId);

    OrderHeader findFirstByOrderByOrderHeaderIdDesc();

}
