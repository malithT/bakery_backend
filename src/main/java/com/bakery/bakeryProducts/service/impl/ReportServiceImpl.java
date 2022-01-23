package com.bakery.bakeryProducts.service.impl;

import com.bakery.bakeryProducts.entity.OrderHeader;
import com.bakery.bakeryProducts.repository.ReportRepository;
import com.bakery.bakeryProducts.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    public final ReportRepository reportRepository;

    @Override
    public List<Map<String, String>> monthlySummary() {
        return null;
    }

    @Override
    public List<Map<String, String>> monthlyCancelSummary() {
        return null;
    }

    @Override
    public List<Map<String, String>> topSelling() {
        return null;
    }

    @Override
    public List<OrderHeader> newOrder() {
        return null;
    }
}
