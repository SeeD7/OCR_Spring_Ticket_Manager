package org.example.demo.ticket.consumer.impl.rowmapper;

import org.example.demo.ticket.consumer.contract.dao.ProjetDao;
import org.example.demo.ticket.consumer.contract.dao.TicketStatutDao;
import org.example.demo.ticket.model.bean.ticket.Evolution;
import org.springframework.jdbc.core.RowMapper;

public abstract class EvolutionRM {

  public static RowMapper<Evolution> getRowMapper() {
    return (pRS, pRowNum) -> {
      Evolution vEvolution = new Evolution(pRS.getLong("ticket_numero"));
      vEvolution.setPriorite(pRS.getInt("priorite"));
      return vEvolution;
    };
  }

  public static RowMapper<Evolution> getTicketRowMapper(ProjetDao vProjetDao, TicketStatutDao vTicketStatutDao) {
    return (pRS, pRowNum) -> {
      Evolution vEvolution = new Evolution(pRS.getLong("numero"));
      vEvolution.setTitre(pRS.getString("titre"));
      vEvolution.setDate(pRS.getDate("date"));
      vEvolution.setDescription(pRS.getString("description"));
      vEvolution.setProjet(vProjetDao.getProjet(pRS.getInt("projet_id")));
      vEvolution.setStatut(vTicketStatutDao.getStatut(pRS.getInt("statut_actuel_id")));
      return vEvolution;
    };
  }
}
