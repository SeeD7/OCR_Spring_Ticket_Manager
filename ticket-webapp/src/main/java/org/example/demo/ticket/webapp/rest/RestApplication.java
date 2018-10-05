package org.example.demo.ticket.webapp.rest;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


/**
 * Classe de configuration de l'application REST
 *
 * @author lgu
 */
@ApplicationPath("/")
public class RestApplication extends ResourceConfig {

    /**
     * Constructeur par défaut.
     */
    public RestApplication() {
        packages("org.example.demo.ticket.webapp.rest");
    }
}
