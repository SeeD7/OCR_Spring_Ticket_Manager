package org.example.demo.ticket.business.manager;


import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.projet.Version;
import org.example.demo.ticket.model.exception.NotFoundException;

import java.util.List;


/**
 * Manager des beans du package Projet.
 *
 * @author lgu
 */
public interface ProjetManager {

  /**
   * Renvoie le projet demandé
   *
   * @param pId l'identifiant du projet
   * @return Le {@link Projet}
   * @throws NotFoundException Si le projet n'est pas trouvé
   */
  Projet getProjet(Integer pId) throws NotFoundException;

  /**
   * Renvoie la liste des {@link Projet}
   *
   * @return List
   */
  List<Projet> getListProjet();

  /**
   * Insére une nouvelle {@link Version} d'un {@link Projet} si elle est valide
   */
  void insertVersion(Version pVersion);
}
