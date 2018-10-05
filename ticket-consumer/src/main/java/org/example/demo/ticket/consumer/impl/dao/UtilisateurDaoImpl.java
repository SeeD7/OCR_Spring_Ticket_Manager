package org.example.demo.ticket.consumer.impl.dao;

import org.example.demo.ticket.consumer.contract.dao.UtilisateurDao;
import org.example.demo.ticket.consumer.impl.rowmapper.UtilisateurRM;
import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.inject.Named;
import java.util.List;

/**
 * Classe d'implémentation de {@link UtilisateurDao}.
 */
@Named
public class UtilisateurDaoImpl extends AbstractDaoImpl implements UtilisateurDao {

  private static final String SQL_SELECT = "SELECT * FROM public.utilisateur";

  @Override
  public int getCountUtilisateur() {
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    return vJdbcTemplate.queryForObject("SELECT COUNT(*) FROM utilisateur", Integer.class);
  }
  
  @Override
  public List<Utilisateur> getAllUtilisateurs() {
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    return vJdbcTemplate.query(SQL_SELECT, UtilisateurRM.getRowMapper());
  }

  @Override
  public Utilisateur getUtilisateur(int id) {
    String vSQL = SQL_SELECT + " WHERE id = ?";
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    return vJdbcTemplate.queryForObject(vSQL, UtilisateurRM.getRowMapper(), id);
  }

  @Override
  public void updateUtilisateur(Utilisateur pUtilisateur) {
    String vSQL = "UPDATE utilisateur SET nom = :nom WHERE id = :id";
    SqlParameterSource vParams = new BeanPropertySqlParameterSource(pUtilisateur);
    NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
    vJdbcTemplate.update(vSQL, vParams);
  }

  @Override
  public void deleteObject(int id){
    String vSQL = "DELETE FROM utilisateur WHERE id = :id";
    NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
    MapSqlParameterSource vParams = new MapSqlParameterSource();
    vParams.addValue("id", id);
    vJdbcTemplate.update(vSQL, vParams);
  }
}