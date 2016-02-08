package com.newsWeaver.lotery.ticket.Resource;

import io.dropwizard.hibernate.UnitOfWork;









import java.io.IOException;
import java.util.ArrayList;
import java.util.List;









import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;









import com.newsWeaver.lotery.converter.Converter;
import com.newsWeaver.lotery.model.Ticket;
import com.newsWeaver.lotery.model.TicketStatus;
import com.newsWeaver.lotery.service.TicketService;
import com.newsWeaver.lotery.ticket.dto.TicketDto;
import com.newsWeaver.ticket.Dao.TicketDao;
import com.newsWeaver.util.Serializer;


@Path("/ticket")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Controler {
	
    private	TicketService ticketService;

    
	public Controler(TicketService ticketService){
		this.ticketService=ticketService;
	}

	
	@javax.ws.rs.GET
    @Path("/{id}")
    public TicketDto getTickets(@PathParam("id") String ticketId) {
        return ticketService.getTickets(ticketId);
    }	
	
	@javax.ws.rs.GET
    @Path("/")
	@UnitOfWork
    public List<TicketDto> getAllTickets() {
        return ticketService.getAllTickets();
    }



    
	
//    @Path("/alltickets")
//    public TicketDto getGreeting() {
//		
//  		TicketDto ticket= new TicketDto(5);
//  		ticket.getLoteryNumbers()[0][0]= "1";
//  		ticket.getLoteryNumbers()[1][1]= "2";
//  		ticket.getLoteryNumbers()[2][2]= "3";
//  		ticket.getLoteryNumbers()[2][1]= "4";
//          return ticket;
//      }
//	
//	@javax.ws.rs.GET
//    public TicketDto getList() {
//		
//		TicketDto ticket= new TicketDto(5);
//		ticket.getLoteryNumbers()[0][0]= "1";
//		ticket.getLoteryNumbers()[1][1]= "2";
//		ticket.getLoteryNumbers()[2][2]= "3";
//		ticket.getLoteryNumbers()[2][1]= "4";
//
//        return ticket;
//    }
	
	
	
//	@javax.ws.rs.GET
//    public TicketDto getGreeting(@PathParam("id") String ticketId) {
//		
//		TicketDto ticket= new TicketDto(5);
//		ticket.getLoteryNumbers()[0][0]= "1";
//		ticket.getLoteryNumbers()[1][1]= "2";
//		ticket.getLoteryNumbers()[2][2]= "3";
//		ticket.getLoteryNumbers()[2][1]= "4";
//
//        return ticket;
//    }
//	
	
	@javax.ws.rs.POST
    @Path("/")
	@UnitOfWork
    public TicketDto create(TicketDto ticketDto) {
        return ticketService.create();
    }
	
	

	@javax.ws.rs.PUT
    @Path("/{id}")
	@UnitOfWork
    public TicketDto amend(@PathParam("id") String ticketId, TicketDto ticketDto) {
        return ticketService.amend(ticketId, ticketDto);
    }
	
	@javax.ws.rs.PUT
	@Path("/status/{id}")
	@UnitOfWork
    public TicketStatus getStatus(@PathParam("id") String ticketId) {
		
		return ticketService.getStatus(ticketId);
	
    }
	
//	/ticket POST Create a ticket
//
//	/ticket GET Return list of tickets
//
//	/ticket/{id} GET Get individual ticket
//
//	/ticket/{id} PUT Amend ticket lines
//
//	/status/{id} PUT Retrieve status of ticket

}
