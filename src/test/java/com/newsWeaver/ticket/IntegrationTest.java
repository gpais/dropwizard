package com.newsWeaver.ticket;


import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import com.newsWeaver.lotery.model.TicketStatus;
import com.newsWeaver.lotery.ticket.dto.TicketDto;
import com.newsWeaver.rules.TicketLine;

public class IntegrationTest {

    private static final String TMP_FILE = createTempFile();
    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("example.yml");

    @ClassRule
    public static final DropwizardAppRule<LoteryConfiguration> RULE = new DropwizardAppRule<>(
    		LoteryApllication.class, CONFIG_PATH,
            ConfigOverride.config("database.url", "jdbc:h2:" + TMP_FILE));

    private Client client;

    @BeforeClass
    public static void migrateDb() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        RULE.getApplication().run("db", "migrate", CONFIG_PATH);
        client = ClientBuilder.newClient();
    }

    @After
    public void tearDown() throws Exception {
        RULE.getApplication().run("db", "drop-all","--confirm-delete-everything", CONFIG_PATH);
        client.close();
    }

    private static String createTempFile() {
        try {
            return File.createTempFile("test-example", null).getAbsolutePath();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void testCreateTicket() throws Exception {
        TicketDto ticketDto= new TicketDto();
        ticketDto.setTicketStatus(TicketStatus.unchecked);
        ticketDto.setId(1l);
        
        final TicketDto ticket = client.target("http://localhost:" + RULE.getLocalPort() + "/ticket/")
               .request()
               .post(Entity.entity(ticketDto, MediaType.APPLICATION_JSON_TYPE))
               .readEntity(TicketDto.class);
        
       assertThat(ticket.getTicketStatus()).isEqualTo(ticketDto.getTicketStatus());
       assertThat(ticket.getId()).isEqualTo(ticketDto.getId());

    }
    
    
    @Test
    public void testGetAllTickets() throws Exception {
        TicketDto ticketDto= new TicketDto();
        ticketDto.setTicketStatus(TicketStatus.unchecked);
        ticketDto.setId(1l);
        
        //create ticket
        final TicketDto ticket = client.target("http://localhost:" + RULE.getLocalPort() + "/ticket/")
                .request()
               .post(Entity.entity(ticketDto, MediaType.APPLICATION_JSON_TYPE))
              .readEntity(TicketDto.class);
        
       assertThat(ticket.getTicketStatus()).isEqualTo(ticketDto.getTicketStatus());
       assertThat(ticket.getId()).isEqualTo(ticketDto.getId());

       
       final List<TicketDto>  tickets = client.target("http://localhost:" + RULE.getLocalPort() + "/ticket/")
             .request()
             .accept(MediaType.APPLICATION_JSON_TYPE)
             .get(new GenericType<List<TicketDto>>() {} );
       

       
       assertThat(tickets.size()).isEqualTo(1);
       
       TicketDto ticketStored = tickets.get(0);
       
       assertThat(ticketStored.getId()).isEqualTo(1l);
       assertThat(ticketStored.getLoteryNumbers()).isEqualTo(ticket.getLoteryNumbers());
       assertThat(ticketStored.getTicketStatus()).isEqualTo(TicketStatus.unchecked);

    }
    
    
    @Test
    public void testAmendTicket() throws Exception {
        TicketDto ticketDto= new TicketDto();
        ticketDto.setTicketStatus(TicketStatus.unchecked);
        ticketDto.setId(1l);
        
        //create ticket
        final TicketDto ticket = client.target("http://localhost:" + RULE.getLocalPort() + "/ticket/")
                .request()
               .post(Entity.entity(ticketDto, MediaType.APPLICATION_JSON_TYPE))
              .readEntity(TicketDto.class);
        
       assertThat(ticket.getTicketStatus()).isEqualTo(ticketDto.getTicketStatus());
       assertThat(ticket.getId()).isEqualTo(ticketDto.getId());

       
       //create second ticket
       final TicketDto secondticket = client.target("http://localhost:" + RULE.getLocalPort() + "/ticket/")
               .request()
              .post(Entity.entity(ticketDto, MediaType.APPLICATION_JSON_TYPE))
             .readEntity(TicketDto.class);
       
      assertThat(secondticket.getTicketStatus()).isEqualTo(ticketDto.getTicketStatus());
      assertThat(secondticket.getId()).isEqualTo(2l);
      
       
       final TicketDto ticketAmended = client.target("http://localhost:" + RULE.getLocalPort() + "/ticket/")
    		   .path("1")
               .request()
              .put(Entity.entity(secondticket, MediaType.APPLICATION_JSON_TYPE))
             .readEntity(TicketDto.class);
       
    
       assertThat(ticketAmended.getTicketStatus()).isEqualTo(ticketDto.getTicketStatus());
       assertThat(ticketAmended.getId()).isEqualTo(1l);
       assertThat(ticketAmended.getLoteryNumbers()).isEqualTo(merge(ticket.getLoteryNumbers(),secondticket.getLoteryNumbers()));


    }
    
    
    @Test
    public void testAttemptToAmendTicketAfterGetStatus() throws Exception {
        TicketDto ticketDto= new TicketDto();
        ticketDto.setTicketStatus(TicketStatus.unchecked);
        ticketDto.setId(1l);
        
        //create ticket
        final TicketDto ticket = client.target("http://localhost:" + RULE.getLocalPort() + "/ticket/")
                .request()
               .post(Entity.entity(ticketDto, MediaType.APPLICATION_JSON_TYPE))
              .readEntity(TicketDto.class);
        
       assertThat(ticket.getTicketStatus()).isEqualTo(ticketDto.getTicketStatus());
       assertThat(ticket.getId()).isEqualTo(ticketDto.getId());

       
       //create second ticket
       final TicketDto secondticket = client.target("http://localhost:" + RULE.getLocalPort() + "/ticket/")
               .request()
              .post(Entity.entity(ticketDto, MediaType.APPLICATION_JSON_TYPE))
             .readEntity(TicketDto.class);
       
      assertThat(secondticket.getTicketStatus()).isEqualTo(ticketDto.getTicketStatus());
      assertThat(secondticket.getId()).isEqualTo(2l);
      
       
      //validate ticket
      final TicketStatus secondticketTicketStatus = client.target("http://localhost:" + RULE.getLocalPort() + "/ticket/")
    		  .path("status")
    		  .path("2")
              .request()
             .put(Entity.entity(ticketDto, MediaType.APPLICATION_JSON_TYPE))
            .readEntity(TicketStatus.class);
      
     assertThat(secondticketTicketStatus).isEqualTo(TicketStatus.checked);
     assertThat(secondticket.getId()).isEqualTo(2l);
     
     
       final TicketDto ticketAmended = client.target("http://localhost:" + RULE.getLocalPort() + "/ticket/")
    		   .path("1")
               .request()
              .put(Entity.entity(secondticket, MediaType.APPLICATION_JSON_TYPE))
             .readEntity(TicketDto.class);
       
    
       assertThat(ticketAmended.getTicketStatus()).isEqualTo(TicketStatus.unchecked);
       assertThat(ticketAmended.getId()).isEqualTo(1l);
       assertThat(ticketAmended.getLoteryNumbers()).isEqualTo(ticket.getLoteryNumbers());


    }
    
    
    
    
    private String[][] merge(String[][] ticketOne, String[][] ticketTwo) {
		int j = 0;

		List<TicketLine> ticketLines= new ArrayList<TicketLine>();
		
		addTicketToLines(ticketOne, ticketLines);
		addTicketToLines(ticketTwo, ticketLines);
		
		int row= 0;
		
		ticketLines.sort((a,b)->{
		return new BigDecimal(a.getResult()).compareTo(new BigDecimal(b.getResult()));
	    });
		
		String[][] ticket = new String[ticketLines.size()][4];

		
		for(TicketLine line:ticketLines){
			ticket[row][0] = line.getFirstValue().toString();
			ticket[row][1] = line.getSecondValue().toString();
			ticket[row][2] = line.getThirdvalue().toString();
			ticket[row][3] = line.getResult().toString();
			row++;
	    };
		
		return ticket;

	}

	private void addTicketToLines(String[][] ticketOne,
			List<TicketLine> ticketLines) {
		for (int i = 0; i < ticketOne.length; i++) {
			TicketLine ticketLine= new TicketLine();
			ticketLine.setFirstValue(Integer.valueOf(ticketOne[i][0]));
			ticketLine.setSecondValue(Integer.valueOf(ticketOne[i][1]));
			ticketLine.setThirdvalue(Integer.valueOf(ticketOne[i][2]));
			ticketLine.setResult(Integer.valueOf(ticketOne[i][3]));
			ticketLines.add(ticketLine);

		}
	}
   

}
