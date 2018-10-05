package org.example.demo.ticket.consumer.impl.dao;

import org.example.demo.ticket.consumer.contract.dao.ProjetDao;
import org.example.demo.ticket.consumer.contract.dao.UtilisateurDao;
import org.example.demo.ticket.consumer.impl.rowmapper.ProjetRM;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.projet.Version;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Classe d'implémentation de {@link ProjetDao}.
 */
@Named
public class ProjetDaoImpl extends AbstractDaoImpl implements ProjetDao {

  @Inject
  private UtilisateurDao vUtilisateurDao;

  private static final String SQL_SELECT = "SELECT * FROM public.projet";

  @Override
  public int getCountProjet() {
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    return vJdbcTemplate.queryForObject("SELECT COUNT(*) FROM projet", Integer.class);
  }
  
  @Override
  public List<Projet> getAllProjets() {
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    return vJdbcTemplate.query(SQL_SELECT, ProjetRM.getRowMapper(vUtilisateurDao));
  }

  @Override
  public Projet getProjet(int id) {
    String vSQL = SQL_SELECT + " WHERE id = ?";
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    return vJdbcTemplate.queryForObject(vSQL, ProjetRM.getRowMapper(vUtilisateurDao), id);
  }

  @Override
  public void updateProjet(Projet pProjet) {
    String vSQL = "UPDATE projet SET nom = :nom WHERE id = :id";
    SqlParameterSource vParams = new BeanPropertySqlParameterSource(pProjet);
    NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
    vJdbcTemplate.update(vSQL, vParams);
  }

  @Override
  public void deleteObject(int id){
    String vSQL = "DELETE FROM projet WHERE id = :id";
    NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
    MapSqlParameterSource vParams = new MapSqlParameterSource();
    vParams.addValue("id", id);
    vJdbcTemplate.update(vSQL, vParams);
  }

  @Override
  public int createVersion(Version pVersion) {
    String vSQL = "INSERT INTO version(projet_id, numero) VALUES(?,?)";
    MapSqlParameterSource vParams = new MapSqlParameterSource();
    vParams.addValue("projet_id", pVersion.getProjet().getId());
    vParams.addValue("numero", pVersion.getNumero());
    NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
    return vJdbcTemplate.update(vSQL, vParams);
  }
}