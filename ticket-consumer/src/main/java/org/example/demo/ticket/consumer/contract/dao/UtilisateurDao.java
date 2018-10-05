package org.example.demo.ticket.consumer.contract.dao;

import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;

import java.util.List;

/**
 * Interface DAO du package
 * {@link org.example.demo.ticket.model.bean.utilisateur}
 */
public interface UtilisateurDao {

  int getCountUtilisateur();

  List<Utilisateur> getAllUtilisateurs();

  Utilisateur getUtilisateur(int id);
  
  void updateUtilisateur(Utilisateur pUtilisateur);

  void deleteObject(int id);
}