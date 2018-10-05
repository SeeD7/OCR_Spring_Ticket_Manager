package org.example.demo.ticket.business.manager.impl;


import org.example.demo.ticket.business.manager.TicketManager;
import org.example.demo.ticket.consumer.contract.dao.TicketDao;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


/**
 * Manager des beans du package Ticket.
 *
 * @author lgu
 */
@Named
@ManagedBean
public class TicketManagerImpl implements TicketManager {

  @Inject
  private TicketDao vTicketDao;

  @Override
  public Ticket getTicket(Long pNumero) throws NotFoundException {
    if (pNumero < 1L) {
      throw new NotFoundException("Ticket non trouvé : numero=" + pNumero);
    }
    return vTicketDao.getTicket(pNumero);
  }

  @Override
  public List<Ticket> getListTicket(RechercheTicket pRechercheTicket) {
    return vTicketDao.getListTicket(pRechercheTicket);
  }

  @Override
  public List<Ticket> getListTicket() {
    return vTicketDao.getListTicket();
  }

  @Override
  public int getCountTicket(RechercheTicket pRechercheTicket) {
    return vTicketDao.getCountTicket(pRechercheTicket);
  }
}
