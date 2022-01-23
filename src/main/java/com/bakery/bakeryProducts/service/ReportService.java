package com.bakery.bakeryProducts.service;

import com.bakery.bakeryProducts.entity.OrderHeader;
import net.minidev.json.JSONObject;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReportService {
    List<Double> monthlySummary(String currentYear);
    List<Integer> monthlyCancelSummary(String currentYear);
    List<Map<String,String>> topSelling();
    List<OrderHeader> newOrder(Date deliveryDate);
    List<Integer> completedOrders(String year);
}
