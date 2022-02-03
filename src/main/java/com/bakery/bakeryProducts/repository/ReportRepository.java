package com.bakery.bakeryProducts.repository;
import com.bakery.bakeryProducts.entity.OrderHeader;
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


    @Query(nativeQuery = true,value = "SELECT sum(quantity) as orderCount,product_name as productName FROM order_header_details\n" +
            "INNER JOIN order_details on order_details.order_header_id = order_header_details.order_header_id\n" +
            "INNER JOIN product on order_details.product_id = product.product_id\n" +
            "where month= :month and year=:year " +
            "GROUP BY product_name\n" +
            "ORDER BY sum(quantity) DESC\n" +
            "LIMIT 3")
    List<Map<String, String>> topSelling(String month,String year);

    @Query(nativeQuery = true,value = "SELECT *" +
            "FROM order_header_details WHERE delivery_date >= :deliveryDate AND order_status != 'CANCELLED'")
    List<OrderHeader> newOrder(Date deliveryDate);

    @Query(nativeQuery = true,value = "SELECT COUNT(order_status) as completedOrders,`year`,`month` from order_header_details WHERE order_status = 'COMPLETED'\n" +
            "AND `year` =:year\n" +
            "GROUP BY `month`")
    List<Object[]> completedOrders(String year);

    @Query(nativeQuery = true,value = "select order_details.order_header_id,order_details.product_category_id,order_details.product_id,\n" +
            "order_details.order_detail_id,order_details.quantity,order_details.amount from order_header_details\n" +
            "INNER JOIN order_details on order_header_details.order_header_id = order_details.order_header_id\n" +
            "INNER JOIN product on product.product_id = order_details.product_id\n" +
            "INNER JOIN product_category on product.product_category_id = product_category.product_category_id\n" +
            "WHERE \n" +
            "delivery_date BETWEEN :dateFrom AND :dateTo \n" +
            "AND if(:productCategoryId is null,true,order_details.product_category_id = :productCategoryId)\n" +
            "AND if(:productId is null,true,order_details.product_id = :productId)" +
            "AND if(:customerName is null,true,customer_name = :customerName)")
    List<Object[]> searchData(Date dateTo, Date dateFrom,Integer productCategoryId,Integer productId,String customerName);


    @Query(nativeQuery = true,value = "select product_name as productName,sum(quantity) as productCount from order_header_details\n" +
            "INNER JOIN order_details on order_header_details.order_header_id = order_details.order_header_id\n" +
            "INNER JOIN product on product.product_id = order_details.product_id\n" +
            "INNER JOIN product_category on product.product_category_id = product_category.product_category_id\n" +
            "WHERE \n" +
            "delivery_date BETWEEN :dateFrom AND :dateTo \n" +
            "AND if(:productCategoryId is null,true,order_details.product_category_id = :productCategoryId)\n" +
            "AND if(:productId is null,true,order_details.product_id = :productId)" +
            "AND if(:customerName is null,true,customer_name = :customerName) " +
            "group by product_name " +
            "ORDER BY count(product_name) DESC " +
            "LIMIT 5")
    List<Object[]> getProductCount(Date dateTo, Date dateFrom, Integer productCategoryId, Integer productId, String customerName);

}
