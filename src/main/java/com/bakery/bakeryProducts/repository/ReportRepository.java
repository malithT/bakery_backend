package com.bakery.bakeryProducts.repository;

import com.bakery.bakeryProducts.dto.MonthlySummeryDto;
import com.bakery.bakeryProducts.entity.OrderHeader;
import net.minidev.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ReportRepository extends JpaRepository<OrderHeader,Integer> {

    @Query(nativeQuery = true,value = "SELECT SUM(tot_amount) as totalAmount,`year`,`month` FROM order_header_details\n" +
            "WHERE `year` = :year AND order_status != 'CANCELLED'" +
            "GROUP BY `month`")
    List<Object[]> monthlySummary(String year);

    @Query(nativeQuery = true,value = "    SELECT COUNT(order_status) AS totalCancellations,`year`,`month` FROM order_header_details\n" +
            "    WHERE `year` =:year and order_status = 'CANCELLED'\n" +
            "    GROUP BY `month`")
    List<Object[]> monthlyCancelledSummary(String year);


    @Query(nativeQuery = true,value = "SELECT count(product_name) as orderCount,product_name as productName FROM order_header_details\n" +
            "INNER JOIN order_details on order_details.order_header_id = order_header_details.order_header_id\n" +
            "INNER JOIN product on order_details.product_id = product.product_id\n" +
            "GROUP BY product_name\n" +
            "ORDER BY count(product_name) DESC\n" +
            "LIMIT 3")
    List<Map<String, String>> topSelling();

    @Query(nativeQuery = true,value = "SELECT *" +
            "FROM order_header_details WHERE delivery_date >= :deliveryDate AND order_status != 'CANCELLED'")
    List<OrderHeader> newOrder(Date deliveryDate);

    @Query(nativeQuery = true,value = "SELECT COUNT(order_status) as completedOrders,`year`,`month` from order_header_details WHERE order_status = 'COMPLETED'\n" +
            "AND `year` =:year\n" +
            "GROUP BY `month`")
    List<Object[]> completedOrders(String year);
}
