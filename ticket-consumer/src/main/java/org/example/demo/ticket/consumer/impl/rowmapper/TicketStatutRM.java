package org.example.demo.ticket.consumer.impl.rowmapper;

import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.springframework.jdbc.core.RowMapper;

public abstract class TicketStatutRM {

  public static RowMapper<TicketStatut> getRowMapper() {
    return (pRS, pRowNum) -> {
      TicketStatut vTicketStatut = new TicketStatut(pRS.getInt("id"));
      vTicketStatut.setLibelle(pRS.getString("libelle"));
      return vTicketStatut;
    };
  }
}
