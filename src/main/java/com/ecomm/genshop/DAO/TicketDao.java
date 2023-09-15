package com.ecomm.genshop.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.genshop.model.Ticket;

@Repository
public interface TicketDao extends JpaRepository<Ticket, Integer> {

}
