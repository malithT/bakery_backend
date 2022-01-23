package com.bakery.bakeryProducts.controller;

import com.bakery.bakeryProducts.dto.CustomOrderDetails;
import com.bakery.bakeryProducts.entity.OrderHeader;
import com.bakery.bakeryProducts.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {

    public final ReportService reportService;

    @GetMapping("/monthlySummary")
    public List<Map<String, String>> monthlySummary(){
        return  reportService.monthlySummary();
    }

    @GetMapping("/monthlyCancelSummary")
    public List<Map<String, String>> monthlyCancelSummary(){
        return  reportService.monthlyCancelSummary();
    }

    @GetMapping("/topSelling")
    public List<Map<String, String>> topSelling(){
        return  reportService.topSelling();
    }

    @GetMapping("/newOrder")
    public List<OrderHeader> newOrder(){
        return  reportService.newOrder();
    }
}
