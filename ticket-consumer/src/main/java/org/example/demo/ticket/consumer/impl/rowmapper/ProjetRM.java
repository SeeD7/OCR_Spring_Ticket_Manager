package org.example.demo.ticket.consumer.impl.rowmapper;

import org.example.demo.ticket.consumer.contract.dao.UtilisateurDao;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.springframework.jdbc.core.RowMapper;

public abstract class ProjetRM {

  public static RowMapper<Projet> getRowMapper(UtilisateurDao vUtilisateurDao) {
    return (pRS, pRowNum) -> {
      Projet vProjet = new Projet(pRS.getInt("id"));
      vProjet.setNom(pRS.getString("nom"));
      vProjet.setDateCreation(pRS.getDate("date_creation"));
      vProjet.setCloture(pRS.getBoolean("cloture"));
      vProjet.setResponsable(vUtilisateurDao.getUtilisateur(pRS.getInt("responsable_id")));
      return vProjet;
    };
  }
}
