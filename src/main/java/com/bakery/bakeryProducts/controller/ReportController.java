package com.bakery.bakeryProducts.controller;

import com.bakery.bakeryProducts.dto.CustomOrderDetails;
import com.bakery.bakeryProducts.dto.SearchData;
import com.bakery.bakeryProducts.dto.SearchDataResponse;
import com.bakery.bakeryProducts.entity.OrderHeader;
import com.bakery.bakeryProducts.service.ReportService;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {

    public final ReportService reportService;

    @PostMapping("/monthlySummary")
    public List<Double> monthlySummary(@RequestParam ("currentYear") String currentYear){
        return  reportService.monthlySummary(currentYear);
    }

    @PostMapping("/monthlyCancelSummary")
    public List<Integer> monthlyCancelSummary(@RequestParam ("currentYear") String currentYear){
        return reportService.monthlyCancelSummary(currentYear);
    }

    @PostMapping("/topSelling")
    public List<Map<String, String>> topSelling(@RequestParam ("month") String month,
                                                @RequestParam ("year") String year){
        return  reportService.topSelling(month,year);
    }

    @PostMapping("/newOrder")
    public List<OrderHeader> newOrder(@RequestParam ("deliveryDate") Date deliveryDate){
        return  reportService.newOrder(deliveryDate);
    }

    @PostMapping("/completedOrders")
    public List<Integer> completedOrders(@RequestParam ("currentYear") String currentYear){
        return  reportService.completedOrders(currentYear);
    }

    @PostMapping("/searchData")
    public SearchDataResponse searchData(@RequestBody SearchData searchData){
        return  reportService.searchData(searchData);
    }
}
