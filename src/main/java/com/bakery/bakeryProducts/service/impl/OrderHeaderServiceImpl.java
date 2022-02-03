package com.bakery.bakeryProducts.service.impl;
import com.bakery.bakeryProducts.entity.OrderHeader;
import com.bakery.bakeryProducts.repository.OrderHeaderRepository;
import com.bakery.bakeryProducts.service.OrderHeaderService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderHeaderServiceImpl implements OrderHeaderService {

    private final OrderHeaderRepository orderHeaderRepository;

    @Override
    public List<OrderHeader> getAllHeaderOrders() {
        try{
            orderHeaderRepository.findAll();
        }catch(Exception e){
            e.printStackTrace();
        }
        return orderHeaderRepository.findAll();
    }

    @Override
    public String editHeaderOrder(OrderHeader orderHeader) {
        JSONObject alert = new JSONObject();
        String month;
        if(orderHeader.getMonth().length() == 1){
            month = "0" + orderHeader.getMonth();
        }else{
            month = orderHeader.getMonth();
        }
        orderHeaderRepository.editHeaderOrder(orderHeader.getOrderHeaderId(), orderHeader.getCustomerMobile(),
                                                orderHeader.getCustomerName(), orderHeader.getDeliveryDate(),
                                                orderHeader.getOrderStatus(),orderHeader.getYear(),month);
        alert.put("message","Order Updated Successfully");
        return alert.toString() ;
    }

    @Override
    public Optional<OrderHeader> findOrderById(int orderId) {
        return orderHeaderRepository.findById(orderId);
    }

    @Override
    public List<OrderHeader> getUpcomingOrders(String deliveryDate) {
        Date date = null;
        
        try {
           date = new SimpleDateFormat("yyyy-MM-dd").parse(deliveryDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return orderHeaderRepository.getUpcomingOrders(date);
    }

}
