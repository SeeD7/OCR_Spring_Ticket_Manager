package org.example.demo.ticket.consumer.contract.dao;

import org.example.demo.ticket.model.bean.ticket.TicketStatut;

import java.util.List;

/**
 * Interface DAO du package
 * {@link org.example.demo.ticket.model.bean.ticket}
 */
public interface TicketStatutDao {

  TicketStatut getStatut(int id);
  List<TicketStatut> getListStatut();
  void updateTicketStatut(TicketStatut pTicketStatut);

  void deleteObject(Long id);
}