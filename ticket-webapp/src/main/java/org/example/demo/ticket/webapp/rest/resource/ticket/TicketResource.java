package org.example.demo.ticket.webapp.rest.resource.ticket;

import org.example.demo.ticket.business.manager.TicketManager;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * Ressource REST pour les {@link Ticket}.
 *
 * @author lgu
 */
@Path("/tickets")
@Produces(MediaType.APPLICATION_JSON)
public class TicketResource {

  @Inject
  private TicketManager vTicketManager;

  /**
   * Renvoie le {@link Ticket} de numéro {@code pNumero}
   *
   * @param pNumero numéro du {@link Ticket}
   * @return Le {@link Ticket}
   * @throws NotFoundException Si le {@link Ticket} n'a pas été trouvé
   */
  @GET
  @Path("{numero}")
  public Ticket get(@PathParam("numero") Long pNumero) throws NotFoundException {
    return vTicketManager.getTicket(pNumero);
  }

  /**
   * Recherche et renvoie les {@link Ticket} correspondant aux critères.
   *
   * @param pProjetId identifiant du {@link org.example.demo.ticket.model.bean.projet.Projet}
   * @return List
   */
  @GET
  @Path("search")
  public List<Ticket> search(@QueryParam("projetId") Integer pProjetId) {
    return vTicketManager.getListTicket(new RechercheTicket().setProjetId(pProjetId));
  }
}
