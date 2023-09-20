package com.ecomm.genshop.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ecomm.genshop.model.Account;
import com.ecomm.genshop.model.Orders;

public interface OrderService {

    Orders createOrder(Account user);

    Optional<Orders> getOrder(Account user);

    void generateInvoice(int orderId);

    Orders saveOrder(Orders order);

    Orders getLatestOrderByUser(Account user);

    Optional<Orders> findById(int orderId);

    List<Orders> getAllOrders(String status);

    List<Orders> findByPayment(int paymentId);

    List<Orders> findByStatus(String status);

    List<Orders> findAllOrders();

    List<Orders> findOrdersByDate(Date startDate, Date endDate);

}
