package org.example.demo.ticket.consumer.impl.rowmapper;

import org.example.demo.ticket.model.bean.ticket.BugNiveau;
import org.springframework.jdbc.core.RowMapper;

public abstract class BugNiveauRM {

  public static RowMapper<BugNiveau> getRowMapper() {
    return (pRS, pRowNum) -> {
      BugNiveau vBugNiveau = new BugNiveau(pRS.getInt("id"));
      vBugNiveau.setOrdre(pRS.getInt("ordre"));
      vBugNiveau.setLibelle(pRS.getString("libelle"));
      return vBugNiveau;
    };
  }
}
