package org.example.demo.ticket.batch;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.demo.ticket.business.manager.TicketManager;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.exception.TechnicalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Named
@Configuration
@PropertySource(value = "file:ticket-batch/src/data/conf/config.properties")
@ComponentScan
public class Executor {

  /** Logger pour la classe */
  private static final Log LOGGER = LogFactory.getLog(Executor.class);
  private static final String DATE_FORMAT = "yyyy-MM-dd_HH-mm-ss";

  @Value("${export.export_location:}")
  private String exportLocation;

  @Inject
  private TicketManager vTicketManager;

  public void start(String[] pArgs) {

    try {
      if (pArgs.length < 1) {
        throw new TechnicalException("Veuillez préciser le traitement à effectuer !");
      }
      StringBuilder sb = new StringBuilder();
      String vTraitementId = pArgs[0];
      if ("ExportTicketStatus".equals(vTraitementId)) {
        LOGGER.info("Execution du traitement : ExportTicketStatus");
        List<Ticket> lTicket = vTicketManager.getListTicket();
        for(Ticket vTicket : lTicket){
          System.out.println(vTicket.toString());
          sb.append(vTicket.toString()).append("\n");
        }
        File directory = new File(exportLocation);
        if (! directory.exists()){
          directory.mkdir();
        }
        String fileName = exportLocation + "/Export_Ticket_" + DateTimeFormatter.ofPattern(DATE_FORMAT).format(LocalDateTime.now()) + ".txt";
        Files.write(Paths.get(fileName), sb.toString().getBytes());
      } else {
        throw new TechnicalException("Traitement inconnu : " + vTraitementId);
      }
    } catch (Throwable vThrowable) {
      LOGGER.error(vThrowable);
      System.exit(1);
    }
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

}
