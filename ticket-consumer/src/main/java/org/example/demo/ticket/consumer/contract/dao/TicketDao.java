package org.example.demo.ticket.consumer.contract.dao;

import org.example.demo.ticket.model.bean.ticket.Bug;
import org.example.demo.ticket.model.bean.ticket.Evolution;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;

import java.util.List;

/**
 * Interface DAO du package
 * {@link org.example.demo.ticket.model.bean.ticket}
 */
public interface TicketDao {

  int getCountTicket();
  int getCountTicket(RechercheTicket pRechercheTicket);

  Bug getBug(Long id);
  List<Bug> getListBug();
  List<Bug> getListBug(RechercheTicket pRechercheTicket);

  Evolution getEvolution(Long id);
  List<Evolution> getListEvolution();
  List<Evolution> getListEvolution(RechercheTicket pRechercheTicket);

  Ticket getTicket(Long id);
  List<Ticket> getListTicket();
  List<Ticket> getListTicket(RechercheTicket pRechercheTicket);
}