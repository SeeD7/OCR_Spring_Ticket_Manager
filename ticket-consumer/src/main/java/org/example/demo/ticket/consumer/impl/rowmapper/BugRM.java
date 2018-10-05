package org.example.demo.ticket.consumer.impl.rowmapper;

import org.example.demo.ticket.consumer.contract.dao.BugNiveauDao;
import org.example.demo.ticket.consumer.contract.dao.ProjetDao;
import org.example.demo.ticket.consumer.contract.dao.TicketStatutDao;
import org.example.demo.ticket.model.bean.ticket.Bug;
import org.example.demo.ticket.model.bean.ticket.BugNiveau;
import org.springframework.jdbc.core.RowMapper;

public abstract class BugRM {

  public static RowMapper<Bug> getRowMapper(BugNiveauDao vBugNiveauDao) {
    return (pRS, pRowNum) -> {
      Bug vBug = new Bug(pRS.getLong("ticket_numero"));
      vBug.setNiveau(vBugNiveauDao.getBugNiveau(pRS.getInt("niveau_bug_id")));
      return vBug;
    };
  }

  public static RowMapper<Bug> getTicketRowMapper(ProjetDao vProjetDao, TicketStatutDao vTicketStatutDao) {
    return (pRS, pRowNum) -> {
      Bug vBug = new Bug(pRS.getLong("numero"));
      vBug.setTitre(pRS.getString("titre"));
      vBug.setDate(pRS.getDate("date"));
      vBug.setDescription(pRS.getString("description"));
      vBug.setProjet(vProjetDao.getProjet(pRS.getInt("projet_id")));
      vBug.setStatut(vTicketStatutDao.getStatut(pRS.getInt("statut_actuel_id")));
      return vBug;
    };
  }
}
