package org.example.demo.ticket.batch;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.demo.ticket.business.manager.TicketManager;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.exception.TechnicalException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.inject.Inject;
import javax.inject.Named;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * Classe Principale de lancement des Batches.
 *
 * @author lgu
 */
@Named
@Configuration
public class Main {

  @Inject


  /**
   * The entry point of application.
   *
   * @param pArgs the input arguments
   */
  public static void main(String[] pArgs) {

    ApplicationContext vApplicationContext
            = new ClassPathXmlApplicationContext("classpath*:bootstrapContext.xml");

    Executor p = (Executor)vApplicationContext.getBean("executor");
    p.start(pArgs);
  }
}
