package com.bakery.bakeryProducts.service.impl;

import com.bakery.bakeryProducts.dto.*;
import com.bakery.bakeryProducts.entity.OrderDetail;
import com.bakery.bakeryProducts.entity.OrderHeader;
import com.bakery.bakeryProducts.repository.OrderHeaderRepository;
import com.bakery.bakeryProducts.repository.ProductCategoryRepository;
import com.bakery.bakeryProducts.repository.ProductRepository;
import com.bakery.bakeryProducts.repository.ReportRepository;
import com.bakery.bakeryProducts.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingLong;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    public final ReportRepository reportRepository;
    public final OrderHeaderRepository orderHeaderRepository;
    public final ProductCategoryRepository productCategoryRepository;
    public final ProductRepository productRepository;


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
    public List<Map<String, String>> topSelling(String month,String year) {
        return reportRepository.topSelling(month,year);
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

    @Override
    public SearchDataResponse searchData(SearchData searchData) {
        ProductCategoryCount productCategoryCount1 = new ProductCategoryCount();
        List<OrderDetail> searchDataList = new ArrayList<>();
        SearchDataResponse searchDataResponse = new SearchDataResponse();
        StatusCount statusCount = new StatusCount();
        Map<String,Long> productCount = new HashMap<>();
        try {
            List<Object[]> searchResult= reportRepository.searchData(searchData.getDateTo(), searchData.getDateFrom(),
                    searchData.getProductCategoryId(),searchData.getProductId(),searchData.getCustomerName());

            List<String> orderStatus = new ArrayList<>();
            searchResult.forEach(sr -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderHeader(orderHeaderRepository.getById(Integer.parseInt(sr[0].toString())));
                orderDetail.setProductCategory(productCategoryRepository.getById(Integer.parseInt(sr[1].toString())));
                orderDetail.setProduct(productRepository.getById(Integer.parseInt(sr[2].toString())));
                orderDetail.setOrderDetailId(Integer.parseInt(sr[3].toString()));
                orderDetail.setQuantity(Integer.parseInt(sr[4].toString()));
                orderDetail.setAmount((Double) (sr[5]));
                searchDataList.add(orderDetail);
                orderStatus.add(orderDetail.getOrderHeader().getOrderStatus());
            });

            Map<String, Long> result = orderStatus.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


            statusCount.setStatusName(result.keySet());
            statusCount.setStatusCount(result.values());

            List<Object[]> productCategoryCount = reportRepository.getProductCount(searchData.getDateTo(), searchData.getDateFrom(),
                    searchData.getProductCategoryId(),searchData.getProductId(),searchData.getCustomerName());

            productCategoryCount.forEach(pc ->{
                productCount.put(pc[0].toString(), Long.valueOf(pc[1].toString()));
            });

            productCategoryCount1.setProductCategory(productCount.keySet());
            productCategoryCount1.setProductCount(productCount.values());

        }catch (Exception e){
        e.printStackTrace();
        }

        searchDataResponse.setSearchData(searchDataList);
        searchDataResponse.setStatusCount(statusCount);
        searchDataResponse.setProductCategoryCount(productCategoryCount1);

        return searchDataResponse;
    }

}
