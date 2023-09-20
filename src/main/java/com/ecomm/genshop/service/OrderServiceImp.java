package com.ecomm.genshop.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.genshop.DAO.AccountDao;
import com.ecomm.genshop.DAO.AddressDao;
import com.ecomm.genshop.DAO.OrdersDao;
import com.ecomm.genshop.DAO.OrdersItemDao;
import com.ecomm.genshop.DAO.PaymentDao;
import com.ecomm.genshop.model.Account;
import com.ecomm.genshop.model.Address;
import com.ecomm.genshop.model.Orders;
import com.ecomm.genshop.model.OrdersItem;
import com.ecomm.genshop.model.Payment;
import com.ecomm.genshop.service.Process.InvoiceGenerator;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    InvoiceGenerator invoiceGenerator;
    @Autowired
    OrdersDao ordersDao;
    @Autowired
    OrdersItemDao ordersItemDao;
    @Autowired
    AccountDao accountDao;
    @Autowired
    AddressDao addressDao;
    @Autowired
    PaymentDao paymentDao;

    @Override
    public Orders createOrder(Account user) {
        Orders order = new Orders();
        order.setUserId(user.getUserId());
        return ordersDao.save(order);
    }

    @Override
    public Optional<Orders> getOrder(Account user) {
        return ordersDao.findByUserId(user.getUserId()).stream().findFirst();
    }

    @Override
    public void generateInvoice(int orderId) {
        Orders order = ordersDao.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        Account account = accountDao.findById(order.getUserId())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Address address = addressDao.findFirstByUserId(account.getUserId())
                .orElseThrow(() -> new RuntimeException("Address not found"));
        Payment payment = paymentDao.findById(order.getPaymentId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        List<OrdersItem> orderItem = getOrderItems(orderId);

        invoiceGenerator.generateInvoice(order, address, account, payment, orderItem);
    }

    @Override
    public Orders saveOrder(Orders order) {
        return ordersDao.save(order);
    }

    @Override
    public Orders getLatestOrderByUser(Account user) {
        return ordersDao.findByOrderByOrderIdDesc().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Optional<Orders> findById(int orderId) {
        return ordersDao.findById(orderId);
    }

    @Override
    public List<Orders> getAllOrders(String status) {
        return ordersDao.getAllOrders(status);
    }

    @Override
    public List<Orders> findByPayment(int paymentId) {
        return ordersDao.findByPaymentId(paymentId);
    }

    @Override
    public List<Orders> findByStatus(String status) {
        return ordersDao.findByStatus(status);
    }

    @Override
    public List<Orders> findAllOrders() {
        return ordersDao.findAll();
    }

    @Override
    public List<Orders> findOrdersByDate(Date startDate, Date endDate) {
        Date start = new Date(startDate.getTime());
        Date end = new Date(endDate.getTime());
        return ordersDao.findByCreatedDateBetween(start, end);
    }

    List<OrdersItem> getOrderItems(int orderId) {
        return ordersItemDao.findByOrderId(orderId);
    }

}
