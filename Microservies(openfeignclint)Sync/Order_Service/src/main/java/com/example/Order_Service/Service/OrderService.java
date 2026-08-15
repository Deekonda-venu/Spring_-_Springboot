package com.example.Order_Service.Service;

import com.example.Order_Service.Clinet.CustomerClinet;
import com.example.Order_Service.Clinet.Menuitemsclinet;
import com.example.Order_Service.Clinet.ResturnatClinet;
import com.example.Order_Service.Model.OrderDetails;
import com.example.Order_Service.Model.OrderItems;
import com.example.Order_Service.Repositary.OrderDetailesRepo;
import com.example.Order_Service.Repositary.OrderitemsRepo;
import com.example.Order_Service.Response.AddressRespose;
import com.example.Order_Service.Response.CustomerResponse;
import com.example.Order_Service.Response.MenuitemsResponse;
import com.example.Order_Service.Response.OrderResponse;
import com.example.Order_Service.Response.ResturantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDetailesRepo orderDetailesRepo;
    @Autowired
    private OrderitemsRepo orderitemsRepo;
    @Autowired
    private ResturnatClinet resturnatClinet;
    @Autowired
    private CustomerClinet customerClinet;
    @Autowired
    private Menuitemsclinet menuitemsclinet;

    public OrderResponse createOrder(OrderDetails orderDetails) {

        // 1. validate customer
        CustomerResponse customerResponse =
                customerClinet.getCustomerById(orderDetails.getCustomerId());

        // 2. validate delivery address belongs to customer, attach it
        AddressRespose addressResponse = customerClinet.getAddressByCustomerAndAddressId(
                orderDetails.getCustomerId(), orderDetails.getDeliveryAddressId());
        customerResponse.setAddress(addressResponse);

        // 3. validate restaurant is OPEN
        ResturantResponse resturantResponse =
                resturnatClinet.getResturentDetailsById(orderDetails.getRestaurantId());
        if (!"OPEN".equalsIgnoreCase(resturantResponse.getStatus())) {
            throw new RuntimeException("Restaurant is not OPEN");
        }

        // 4. fetch each menu item + calculate subtotal
        List<MenuitemsResponse> menuitems = new ArrayList<>();
        BigDecimal subtotal = BigDecimal.ZERO;
        for (OrderItems orderItem : orderDetails.getItems()) {
            MenuitemsResponse menuitem =
                    menuitemsclinet.getMenuitemById(orderItem.getMenuItemId());
            BigDecimal lineTotal = BigDecimal.valueOf(menuitem.getPrice())
                    .multiply(BigDecimal.valueOf(orderItem.getQuantity()));
            subtotal = subtotal.add(lineTotal);
            menuitems.add(menuitem);
        }

        // 5. price calculation
        BigDecimal tax = subtotal.multiply(BigDecimal.valueOf(0.05))
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal deliveryFee = BigDecimal.valueOf(40);
        BigDecimal totalAmount = subtotal.add(tax).add(deliveryFee);

        // 6. save order
        orderDetails.setStatus("CREATED");
        orderDetails.setSubtotal(subtotal);
        orderDetails.setTax(tax);
        orderDetails.setDeliveryFee(deliveryFee);
        orderDetails.setTotalAmount(totalAmount);
        orderDetails.setPaymentStatus("PENDING");
        orderDetails.setCreatedAt(LocalDateTime.now());
        orderDetails.setUpdatedAt(LocalDateTime.now());
        OrderDetails saved = orderDetailesRepo.save(orderDetails);

        // 6b. persist order items
        for (OrderItems item : orderDetails.getItems()) {
            item.setOrderId(saved.getId());
            orderitemsRepo.save(item);
        }

        // 7. build response
        OrderResponse resp = new OrderResponse();
        resp.setOrderId(saved.getId());
        resp.setCustomerDetails(customerResponse);
        resp.setRestaurantDetails(resturantResponse);
        resp.setItems(menuitems);
        resp.setStatus(saved.getStatus());
        resp.setSubtotal(subtotal);
        resp.setTax(tax);
        resp.setDeliveryFee(deliveryFee);
        resp.setTotalAmount(totalAmount);
        resp.setPaymentStatus(saved.getPaymentStatus());
        resp.setCreatedAt(saved.getCreatedAt());
        resp.setUpdatedAt(saved.getUpdatedAt());
        return resp;
    }
    public OrderResponse getOrderById(Long orderId) {
        OrderDetails orderDetails = orderDetailesRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderDetails.setItems(orderitemsRepo.findByOrderId(orderId));
        return createOrderResponse(orderDetails);
    }

    private OrderResponse createOrderResponse(OrderDetails orderDetails) {
        CustomerResponse customerResponse =
                customerClinet.getCustomerById(orderDetails.getCustomerId());

        AddressRespose addressResponse = customerClinet.getAddressByCustomerAndAddressId(
                orderDetails.getCustomerId(), orderDetails.getDeliveryAddressId());
        customerResponse.setAddress(addressResponse);

        ResturantResponse resturantResponse =
                resturnatClinet.getResturentDetailsById(orderDetails.getRestaurantId());

        List<MenuitemsResponse> menuitems = new ArrayList<>();
        if (orderDetails.getItems() != null) {
            for (OrderItems orderItem : orderDetails.getItems()) {
                menuitems.add(menuitemsclinet.getMenuitemById(orderItem.getMenuItemId()));
            }
        }

        OrderResponse resp = new OrderResponse();
        resp.setOrderId(orderDetails.getId());
        resp.setCustomerDetails(customerResponse);
        resp.setRestaurantDetails(resturantResponse);
        resp.setItems(menuitems);
        resp.setStatus(orderDetails.getStatus());
        resp.setSubtotal(orderDetails.getSubtotal());
        resp.setTax(orderDetails.getTax());
        resp.setDeliveryFee(orderDetails.getDeliveryFee());
        resp.setTotalAmount(orderDetails.getTotalAmount());
        resp.setPaymentStatus(orderDetails.getPaymentStatus());
        resp.setCreatedAt(orderDetails.getCreatedAt());
        resp.setUpdatedAt(orderDetails.getUpdatedAt());
        return resp;
    }
}
