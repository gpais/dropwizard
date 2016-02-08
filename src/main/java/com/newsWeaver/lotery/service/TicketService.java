package com.newsWeaver.lotery.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.PathParam;

import com.newsWeaver.lotery.converter.Converter;
import com.newsWeaver.lotery.model.Ticket;
import com.newsWeaver.lotery.model.TicketStatus;
import com.newsWeaver.lotery.ticket.dto.TicketDto;
import com.newsWeaver.rules.TicketGenerator;
import com.newsWeaver.rules.TicketLine;
import com.newsWeaver.ticket.Dao.TicketDao;
import com.newsWeaver.util.Serializer;

public class TicketService {
	
	
	 private TicketDao dao;
	    
		public TicketService(TicketDao dao){
			this.dao=dao;
		}

		
       
	   public TicketDto create() {
		   
		   TicketDto ticketDto = new TicketDto();
		    String[][] loteryTicket= new String[3][4];
		    
//		    Rules.calculateResult(loteryTicket);
		    TicketGenerator.generateTicket(loteryTicket);
		    
			Ticket ticket= new Ticket();
			ticket.setData(Serializer.serialize(loteryTicket));
			ticket.setStatus(TicketStatus.unchecked);

			
			dao.create(ticket);
			
			ticketDto.setTicketStatus(TicketStatus.unchecked);
			ticketDto.setLoteryNumbers(loteryTicket);
			ticketDto.setId(ticket.getId());
	        return ticketDto;
	    }
	   
	   private String[][] merge(String[][] ticketOne, String[][] ticketTwo) {
			int j = 0;

			List<TicketLine> ticketLines= new ArrayList<TicketLine>();
			
			addTicketToList(ticketOne, ticketLines);
			addTicketToList(ticketTwo, ticketLines);
			
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



	private void addTicketToList(String[][] ticketOne,
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
	   
	   public TicketStatus getStatus(@PathParam("id") String ticketId) {
			Ticket ticket=dao.findById(Long.valueOf(ticketId));
			
			ticket.setStatus(TicketStatus.checked);
			
			dao.save(ticket);
			
			return ticket.getStatus();
		
	    }
	   
	    public TicketDto amend( String ticketId, TicketDto ticketDto) {
			Ticket ticket=dao.findById(Long.valueOf(ticketId));
	 
			Ticket amendTicket=dao.findById(Long.valueOf(ticketDto.getId()));
			
			if(ticket.getStatus() == TicketStatus.unchecked &&  amendTicket.getStatus()== TicketStatus.unchecked ){
			
			
			String[][] ticketOne=(String[][]) Serializer.deserialize(ticket.getData());
			
			String[][] secondTicket = (String[][]) Serializer.deserialize(amendTicket.getData());

			String[][] mergedTicket=merge(ticketOne,secondTicket);
			
			ticket.setData(Serializer.serialize(mergedTicket));
			
			amendTicket.setStatus(TicketStatus.checked);
			
			dao.save(ticket);
			
			dao.save(amendTicket);

			}
			
	        return Converter.convertToDto(ticket);
	    }

	    public TicketDto getTickets( String ticketId) {
			Ticket ticket=dao.findById(Long.valueOf(ticketId));
	        return Converter.convertToDto(ticket);
	    }	
	    
	    public List<TicketDto> getAllTickets() {
			List<Ticket> tickets = dao.findAll();
			List<TicketDto> ticketDtos= new ArrayList<TicketDto>();
			
			tickets.stream().forEach(a->{
				TicketDto ticket = Converter.convertToDto(a);
				ticketDtos.add(ticket);
			});
			
	        return ticketDtos;
	    }
	    
}
