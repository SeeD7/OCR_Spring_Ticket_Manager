package org.example.demo.ticket.consumer.impl.rowmapper;

import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

public abstract class UtilisateurRM {

  public static RowMapper<Utilisateur> getRowMapper() {
    return (pRS, pRowNum) -> {
      Utilisateur vUtilisateur = new Utilisateur(pRS.getInt("id"));
      vUtilisateur.setNom(pRS.getString("nom"));
      vUtilisateur.setPrenom(pRS.getString("prenom"));
      return vUtilisateur;
    };
  }
}
