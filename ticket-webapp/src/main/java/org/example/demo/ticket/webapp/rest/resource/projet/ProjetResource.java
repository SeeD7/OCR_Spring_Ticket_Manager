package org.example.demo.ticket.webapp.rest.resource.projet;

import org.example.demo.ticket.business.manager.ProjetManager;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.projet.Version;
import org.example.demo.ticket.model.exception.NotFoundException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;


/**
 * Ressource REST pour les {@link Projet}.
 *
 * @author lgu
 */
@Path("/projets")
@Produces(MediaType.APPLICATION_JSON)
public class ProjetResource {

  @Inject
  private ProjetManager vProjetManager;
  /**
   * Renvoie le {@link Projet} d'identifiant {@code pId}
   *
   * @param pId identifiant du {@link Projet}
   * @return Le {@link Projet}
   * @throws NotFoundException Si le {@link Projet} n'a pas été trouvé
   */
  @GET
  @Path("{id}")
  public Projet get(@PathParam("id") Integer pId) throws NotFoundException {
    Projet vProjet = vProjetManager.getProjet(pId);
    return vProjet;
  }


  /**
   * Renvoie tous les {@link Projet}
   *
   * @return List
   */
  @GET
  public List<Projet> get() {
    List<Projet> vListProjet = vProjetManager.getListProjet();
    return vListProjet;
  }


  @PUT
  @Path("/version")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createVersion(Version pVersion) {
    vProjetManager.createVersion(pVersion);
    //status code 201
    return Response.created(URI.create("")).build();
  }
}
