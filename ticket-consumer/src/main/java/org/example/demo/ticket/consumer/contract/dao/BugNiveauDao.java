package org.example.demo.ticket.consumer.contract.dao;

import org.example.demo.ticket.model.bean.ticket.BugNiveau;

import java.util.List;

/**
 * Interface DAO du package
 * {@link org.example.demo.ticket.model.bean.ticket.BugNiveau}
 */
public interface BugNiveauDao {

  int getCountBugNiveau();

  List<BugNiveau> getAllBugNiveaus();

  BugNiveau getBugNiveau(int id);
  
  void updateBugNiveau(BugNiveau pBugNiveau);

  void deleteObject(int id);
}