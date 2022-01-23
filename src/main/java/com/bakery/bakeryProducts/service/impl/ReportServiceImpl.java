package com.bakery.bakeryProducts.service.impl;

import com.bakery.bakeryProducts.dto.CancellationSummery;
import com.bakery.bakeryProducts.dto.CompletedOrder;
import com.bakery.bakeryProducts.dto.MonthlySummeryDto;
import com.bakery.bakeryProducts.entity.OrderHeader;
import com.bakery.bakeryProducts.repository.ReportRepository;
import com.bakery.bakeryProducts.service.ReportService;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    public final ReportRepository reportRepository;

    @Override
    public List<Double> monthlySummary(String currentYear) {
        List<MonthlySummeryDto> monthlySummeryDtoList = new ArrayList<>();
        List<Double> monthlySummaryList = new ArrayList<>(Collections.nCopies(12, 0.0));
        try {
            List<Object[]> summeryList = reportRepository.monthlySummary(currentYear);

            summeryList.forEach(sl -> {
                MonthlySummeryDto monthlySummeryDto = new MonthlySummeryDto();
                monthlySummeryDto.setTotalAmount(Double.valueOf(sl[0].toString()));
                monthlySummeryDto.setYear(sl[1].toString());
                monthlySummeryDto.setMonth(sl[2].toString());
                monthlySummeryDtoList.add(monthlySummeryDto);
            });

            IntStream.range(0, monthlySummeryDtoList.size()).forEach(index -> {
                monthlySummaryList.set((Integer.parseInt(monthlySummeryDtoList.get(index).getMonth()) - 1), monthlySummeryDtoList.get(index).getTotalAmount() == null ? 0 : monthlySummeryDtoList.get(index).getTotalAmount());
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        return monthlySummaryList;
    }

    @Override
    public List<Integer> monthlyCancelSummary(String currentYear) {
        List<CancellationSummery> monthlyCancellationDtoList = new ArrayList<>();
        List<Integer> monthlyCancellationList = new ArrayList<>(Collections.nCopies(12, 0));

         try{
            List<Object[]> cancellationSummery = reportRepository.monthlyCancelledSummary(currentYear);
            cancellationSummery.forEach(cs ->{
                CancellationSummery cancellationSummeryMapper = new CancellationSummery();
                cancellationSummeryMapper.setTotalCancellations(Integer.parseInt(cs[0].toString()));
                cancellationSummeryMapper.setYear(cs[1].toString());
                cancellationSummeryMapper.setMonth(cs[2].toString());
                monthlyCancellationDtoList.add(cancellationSummeryMapper);
            });
             IntStream.range(0, monthlyCancellationDtoList.size()).forEach(index -> {
                 monthlyCancellationList.set((Integer.parseInt(monthlyCancellationDtoList.get(index).getMonth()) - 1), monthlyCancellationDtoList.get(index).getTotalCancellations() == null ? 0 : monthlyCancellationDtoList.get(index).getTotalCancellations());
             });

         }catch (Exception e){
             e.printStackTrace();
         }


        return monthlyCancellationList;
    }

    @Override
    public List<Map<String, String>> topSelling() {
        return reportRepository.topSelling();
    }

    @Override
    public List<OrderHeader> newOrder(Date deliveryDate) {
        try {
            return reportRepository.newOrder(deliveryDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportRepository.newOrder(deliveryDate);
    }

    @Override
    public List<Integer> completedOrders(String year) {
        List<CompletedOrder> completedOrderList = new ArrayList<>();
        List<Integer> monthlyCompletedOrders = new ArrayList<>(Collections.nCopies(12, 0));

        try{
            List<Object[]> CompletedOrderSummery = reportRepository.completedOrders(year);
            CompletedOrderSummery.forEach(co ->{
                CompletedOrder completedOrder = new CompletedOrder();
                completedOrder.setCompletedOrder(Integer.parseInt(co[0].toString()));
                completedOrder.setYear(co[1].toString());
                completedOrder.setMonth(co[2].toString());
                completedOrderList.add(completedOrder);
            });
            IntStream.range(0, completedOrderList.size()).forEach(index -> {
                monthlyCompletedOrders.set((Integer.parseInt(completedOrderList.get(index).getMonth()) - 1), completedOrderList.get(index).getCompletedOrder() == null ? 0 : completedOrderList.get(index).getCompletedOrder());
            });

        }catch (Exception e){
            e.printStackTrace();
        }


        return monthlyCompletedOrders;
    }

}
