package com.bakery.bakeryProducts.repository;

import com.bakery.bakeryProducts.entity.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReportRepository extends JpaRepository<OrderHeader,Integer> {

//    @Query(nativeQuery = true,value = "")
//    List<Map<String, String>> monthlySummary();
}
