package org.example.demo.ticket.consumer.impl.dao;

import org.example.demo.ticket.consumer.contract.dao.BugNiveauDao;
import org.example.demo.ticket.consumer.impl.rowmapper.BugNiveauRM;
import org.example.demo.ticket.model.bean.ticket.BugNiveau;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.inject.Named;
import java.util.List;

/**
 * Classe d'implémentation de {@link BugNiveauDao}.
 */
@Named
public class BugNiveauDaoImpl extends AbstractDaoImpl implements BugNiveauDao {

  private static final String SQL_SELECT = "SELECT * FROM public.niveau_bug";

  @Override
  public int getCountBugNiveau() {
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    return vJdbcTemplate.queryForObject("SELECT COUNT(*) FROM niveau_bug", Integer.class);
  }
  
  @Override
  public List<BugNiveau> getAllBugNiveaus() {
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    return vJdbcTemplate.query(SQL_SELECT, BugNiveauRM.getRowMapper());
  }

  @Override
  public BugNiveau getBugNiveau(int id) {
    String vSQL = SQL_SELECT + " WHERE id = ?";
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    return vJdbcTemplate.queryForObject(vSQL, BugNiveauRM.getRowMapper(), id);
  }

  @Override
  public void updateBugNiveau(BugNiveau pBugNiveau) {
    String vSQL = "UPDATE niveau_bug SET nom = :nom WHERE id = :id";
    SqlParameterSource vParams = new BeanPropertySqlParameterSource(pBugNiveau);
    NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
    vJdbcTemplate.update(vSQL, vParams);
  }

  @Override
  public void deleteObject(int id){
    String vSQL = "DELETE FROM niveau_bug WHERE id = :id";
    NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
    MapSqlParameterSource vParams = new MapSqlParameterSource();
    vParams.addValue("id", id);
    vJdbcTemplate.update(vSQL, vParams);
  }
}