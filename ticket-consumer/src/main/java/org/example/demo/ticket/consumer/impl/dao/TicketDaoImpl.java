package org.example.demo.ticket.consumer.impl.dao;

import org.example.demo.ticket.consumer.contract.dao.BugNiveauDao;
import org.example.demo.ticket.consumer.contract.dao.ProjetDao;
import org.example.demo.ticket.consumer.contract.dao.TicketDao;
import org.example.demo.ticket.consumer.contract.dao.TicketStatutDao;
import org.example.demo.ticket.consumer.impl.rowmapper.BugRM;
import org.example.demo.ticket.consumer.impl.rowmapper.EvolutionRM;
import org.example.demo.ticket.model.bean.ticket.Bug;
import org.example.demo.ticket.model.bean.ticket.Evolution;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe d'implémentation de {@link TicketDao}.
 */
@Named
public class TicketDaoImpl extends AbstractDaoImpl implements TicketDao {

  @Inject
  private ProjetDao vProjetDao;

  @Inject
  private TicketStatutDao vTicketStatutDao;

  @Inject
  private BugNiveauDao vBugNiveauDao;

  @Override
  public int getCountTicket() {
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    return vJdbcTemplate.queryForObject("SELECT COUNT(*) FROM ticket", Integer.class);
  }

  @Override
  public int getCountTicket(RechercheTicket pRechercheTicket) {
    String vSQL
            = "SELECT COUNT(*) FROM ticket WHERE auteur_id = :auteur_id AND projet_id = :projet_id";

    NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

    MapSqlParameterSource vParams = new MapSqlParameterSource();
    vParams.addValue("auteur_id", pRechercheTicket.getAuteurId());
    vParams.addValue("projet_id", pRechercheTicket.getProjetId());

    return vJdbcTemplate.queryForObject(vSQL, vParams, Integer.class);
  }

  @Override
  public Bug getBug(Long id) {
    String vSQL = "SELECT * FROM public.bug WHERE 'ticket_numero' = ?";
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    Bug vBug = vJdbcTemplate.queryForObject(vSQL, BugRM.getRowMapper(vBugNiveauDao), id);
    vBug.setTicket(getTicketPartForBug(vBug.getNumero()));
    return vBug;
  }

  @Override
  public List<Bug> getListBug() {
    String vSQL = "SELECT * FROM bug";
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    List<Bug> lBug = vJdbcTemplate.query(vSQL, BugRM.getRowMapper(vBugNiveauDao));
    for(Bug vBug: lBug) {
      vBug.setTicket(getTicketPartForBug(vBug.getNumero()));
    }
    return lBug;
  }

  @Override
  public List<Bug> getListBug(RechercheTicket pRechercheTicket) {
    String vSQL = "SELECT * FROM bug WHERE auteur_id = ? AND projet_id = ?";
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    List<Bug> lBug = vJdbcTemplate.query(vSQL, BugRM.getRowMapper(vBugNiveauDao), pRechercheTicket.getAuteurId(), pRechercheTicket.getProjetId());
    for(Bug vBug: lBug) {
      vBug.setTicket(getTicketPartForBug(vBug.getNumero()));
    }
    return lBug;
  }

  @Override
  public Evolution getEvolution(Long id) {
    String vSQL = "SELECT * FROM evolution WHERE 'ticket_numero' = ?";
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    Evolution vEvolution = vJdbcTemplate.queryForObject(vSQL, EvolutionRM.getRowMapper(), id);
    vEvolution.setTicket(getTicketPartForEvolution(vEvolution.getNumero()));
    return vEvolution;
  }

  @Override
  public List<Evolution> getListEvolution() {
    String vSQL = "SELECT * FROM evolution";
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    List<Evolution> lEvolution = vJdbcTemplate.query(vSQL, EvolutionRM.getRowMapper());
    for(Evolution vEvolution: lEvolution){
      vEvolution.setTicket(getTicketPartForEvolution(vEvolution.getNumero()));
    }
    return lEvolution;
  }

  @Override
  public List<Evolution> getListEvolution(RechercheTicket pRechercheTicket) {
    String vSQL = "SELECT * FROM evolution WHERE auteur_id = ? AND projet_id = ?";
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    List<Evolution> lEvolution = vJdbcTemplate.query(vSQL, EvolutionRM.getRowMapper(), pRechercheTicket.getAuteurId(), pRechercheTicket.getProjetId());
    for(Evolution vEvolution: lEvolution){
      vEvolution.setTicket(getTicketPartForEvolution(vEvolution.getNumero()));
    }
    return lEvolution;
  }

  public Bug getTicketPartForBug(Long id) {
    String vSQL = "SELECT * FROM ticket WHERE numero = ?";
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    return vJdbcTemplate.queryForObject(vSQL, BugRM.getTicketRowMapper(vProjetDao, vTicketStatutDao), id);
  }

  public Evolution getTicketPartForEvolution(Long id) {
    String vSQL = "SELECT * FROM ticket WHERE numero = ?";
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    return vJdbcTemplate.queryForObject(vSQL, EvolutionRM.getTicketRowMapper(vProjetDao, vTicketStatutDao), id);
  }

  @Override
  public Ticket getTicket(Long id) {
    Ticket vTicket = null;

    try{
      vTicket = getBug(id);
    } catch (Exception e) {
      System.out.println("Ticket not a bug");
    } finally {
      try{
        vTicket = getEvolution(id);
      } catch (Exception e) {
        System.out.println("Ticket not a Evolution");
      }
    }

    return vTicket;
  }

  @Override
  public List<Ticket> getListTicket() {
    List<Ticket> lTicket = new ArrayList<>();
    lTicket.addAll(getListBug());
    lTicket.addAll(getListEvolution());
    return lTicket.stream().sorted(Comparator.comparing(Ticket::getDate)).collect(Collectors.toList());
  }

  @Override
  public List<Ticket> getListTicket(RechercheTicket pRechercheTicket) {
    List<Ticket> lTicket = new ArrayList<>();
    lTicket.addAll(getListBug(pRechercheTicket));
    lTicket.addAll(getListEvolution(pRechercheTicket));
    return lTicket.stream().sorted(Comparator.comparing(Ticket::getDate)).collect(Collectors.toList());
  }
}