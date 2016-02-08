package com.newsWeaver.ticket;

import com.newsWeaver.rules.TicketGenerator;

import junit.framework.TestCase;

public class TicketGeneratorTest extends TestCase{
	
	
	public void testGenerateTicket(){
		String[][] ticket= new String[3][4];
		
		TicketGenerator.generateTicket(ticket);
		
		for(int row=0; row < ticket.length; row ++ ){
			String[] ticketRow = ticket[row];
			for(int column=0; column <ticketRow.length-1;column++){
				assertTrue("0".equals(ticketRow[column])||"1".equals(ticketRow[column])||"2".equals(ticketRow[column]=="2") );
				
			}
			
			
		}
	}

}
