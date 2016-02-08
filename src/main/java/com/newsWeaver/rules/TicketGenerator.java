package com.newsWeaver.rules;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicketGenerator {

	

	
	public static  void generateTicket(String[][] ticket) {
		
		Random generator= new Random();
		List<TicketLine> ticketLines= new ArrayList<TicketLine>();
		
		int numberOfRows = ticket.length;
		for(int i=0; i<numberOfRows; i++){
			TicketLine ticketLine= new TicketLine();
			ticketLine.setFirstValue(generator.nextInt(2));
			ticketLine.setSecondValue(generator.nextInt(2));
			ticketLine.setThirdvalue(generator.nextInt(2));

			ticketLine.init();
			
			ticketLines.add(ticketLine);
			
		}
		
		ticketLines.sort((a,b)->{
			return new BigDecimal(a.getResult()).compareTo(new BigDecimal(b.getResult()));
		});
		
		int j=0;
		for(TicketLine line :ticketLines){
			ticket[j][0]=line.getFirstValue().toString();
			ticket[j][1]=line.getSecondValue().toString();
			ticket[j][2]=line.getThirdvalue().toString();
			ticket[j][3]=line.getResult().toString();
			j++;
		}

		
		
         
		
	}
	
}
