package org.example.demo.ticket.consumer.impl.dao;

import org.example.demo.ticket.consumer.contract.dao.TicketDao;
import org.example.demo.ticket.consumer.contract.dao.TicketStatutDao;
import org.example.demo.ticket.consumer.impl.rowmapper.TicketStatutRM;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.inject.Named;
import java.sql.Types;
import java.util.List;

/**
 * Classe d'implémentation de {@link TicketDao}.
 */
@Named
public class TicketStatutDaoImpl extends AbstractDaoImpl implements TicketStatutDao {

  private static final String SQL_SELECT = "SELECT * FROM public.statut";

  @Override
  public TicketStatut getStatut(int id) {
    String vSQL = SQL_SELECT + " WHERE id = ?";
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    return vJdbcTemplate.queryForObject(vSQL, TicketStatutRM.getRowMapper(), id);
  }

  @Override
  public List<TicketStatut> getListStatut() {
    JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
    return vJdbcTemplate.query(SQL_SELECT, TicketStatutRM.getRowMapper());
  }

  @Override
  public void updateTicketStatut(TicketStatut pTicketStatut) {
    String vSQL = "UPDATE statut SET libelle = :libelle WHERE id = :id";

    SqlParameterSource vParams = new BeanPropertySqlParameterSource(pTicketStatut);

    NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
    vJdbcTemplate.update(vSQL, vParams);
  }

  @Override
  public void deleteObject(Long id)
  {
    String vSQL = "DELETE FROM ticket WHERE id = :id";
    NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
    MapSqlParameterSource vParams = new MapSqlParameterSource();
    vParams.addValue("id", id, Types.INTEGER);
    vJdbcTemplate.update(vSQL, vParams);
  }
}