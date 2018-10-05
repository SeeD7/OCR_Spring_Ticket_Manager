package org.example.demo.ticket.business.factory;

//...
public final class ManagerFactory {
  /** Instance unique de la classe (design pattern Singleton) */
  private static final ManagerFactory INSTANCE = new ManagerFactory();

  /**
   * Constructeur.
   */
  private ManagerFactory() {
    super();
  }

  /**
   * Renvoie l'instance unique de la classe (design pattern Singleton).
   *
   * @return {@link ManagerFactory}
   */
  public static ManagerFactory getInstance() {
    return ManagerFactory.INSTANCE;
  }


  /*public ProjetManagerImpl getProjetManager() {
    return new ProjetManagerImpl();
  }

  public TicketManagerImpl getTicketManager() {
    return new TicketManagerImpl();
  }*/
//...
}