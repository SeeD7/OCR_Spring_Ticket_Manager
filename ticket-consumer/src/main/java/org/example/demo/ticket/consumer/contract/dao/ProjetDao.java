package org.example.demo.ticket.consumer.contract.dao;

import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.projet.Version;

import java.util.List;

/**
 * Interface DAO du package
 * {@link org.example.demo.ticket.model.bean.projet}
 */
public interface ProjetDao {

  int getCountProjet();

  List<Projet> getAllProjets();

  Projet getProjet(int id);
  
  void updateProjet(Projet pProjet);

  void deleteObject(int id);

  int createVersion(Version pVersion);
}