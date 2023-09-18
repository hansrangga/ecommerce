package com.ecomm.genshop.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.Ticket;

@Repository
public interface TicketDao extends JpaRepository<Ticket, Integer> {

    // Mencari berdasarkan userId
    List<Ticket> findByUserId(int userId);

    // Mencari berdasarkan status
    List<Ticket> findByStatus(String status);

    // Mencari berdasarkan assignSupport
    List<Ticket> findByAssignSupport(String assignSupport);

    // Mencari tiket yang masih terbuka (yang belum memiliki dateClosed)
    List<Ticket> findByDateClosedIsNull();

    // Mencari tiket berdasarkan rentang tanggal dibuka
    List<Ticket> findByDateOpenedBetween(Date startDate, Date endDate);

}
