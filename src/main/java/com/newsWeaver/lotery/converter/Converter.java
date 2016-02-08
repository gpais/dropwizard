package com.newsWeaver.lotery.converter;

import com.newsWeaver.lotery.model.Ticket;
import com.newsWeaver.lotery.ticket.dto.TicketDto;
import com.newsWeaver.util.Serializer;

public class Converter {
	
	public static TicketDto convertToDto(Ticket a) {
		TicketDto ticket= new TicketDto();
		ticket.setId(a.getId());
		String[][] loteryNumbers=null;
		try {
			loteryNumbers = (String[][]) Serializer.deserialize(a.getData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ticket.setLoteryNumbers(loteryNumbers);
		ticket.setTicketStatus(a.getStatus());
		return ticket;
	}
	

}
