package com.bakery.bakeryProducts.service;

import com.bakery.bakeryProducts.entity.OrderHeader;

import java.util.List;
import java.util.Map;

public interface ReportService {
    List<Map<String,String>> monthlySummary();
    List<Map<String,String>> monthlyCancelSummary();
    List<Map<String,String>> topSelling();
    List<OrderHeader> newOrder();
}
