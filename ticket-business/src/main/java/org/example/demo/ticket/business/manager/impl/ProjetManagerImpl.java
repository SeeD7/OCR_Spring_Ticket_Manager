package org.example.demo.ticket.business.manager.impl;


import org.example.demo.ticket.business.manager.ProjetManager;
import org.example.demo.ticket.consumer.contract.dao.ProjetDao;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.projet.Version;
import org.example.demo.ticket.model.exception.NotFoundException;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


/**
 * Manager des beans du package Projet.
 *
 * @author lgu
 */
@Named
@ManagedBean
public class ProjetManagerImpl implements ProjetManager {

  @Inject
  private ProjetDao vProjectDao;

  @Override
  public Projet getProjet(Integer pId) throws NotFoundException {
    if (pId < 1) {
      throw new NotFoundException("Projet non trouvé : ID=" + pId);
    }
    return vProjectDao.getProjet(pId);
  }


  @Override
  public List<Projet> getListProjet() {
    return vProjectDao.getAllProjets();
  }

  @Override
  public void insertVersion(Version pVersion) {
    if (!pVersion.getNumero().isEmpty() && pVersion.getProjet() != null){
      vProjectDao.getAllProjets();
    }
  }
}
