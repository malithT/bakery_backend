package com.bakery.bakeryProducts.service;

import com.bakery.bakeryProducts.dto.SearchData;
import com.bakery.bakeryProducts.dto.SearchDataResponse;
import com.bakery.bakeryProducts.entity.OrderHeader;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReportService {
    List<Double> monthlySummary(String currentYear);
    List<Integer> monthlyCancelSummary(String currentYear);
    List<Map<String,String>> topSelling(String month,String year);
    List<OrderHeader> newOrder(Date deliveryDate);
    List<Integer> completedOrders(String year);
    SearchDataResponse searchData(SearchData searchData);
}
