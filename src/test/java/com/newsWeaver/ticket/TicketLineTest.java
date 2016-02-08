package com.newsWeaver.ticket;

import com.newsWeaver.rules.TicketLine;

import junit.framework.TestCase;

public class TicketLineTest extends TestCase {

	
	public void testAllTheOtherCases() {
		TicketLine ticketLine = new TicketLine(2, 1, 2);
		ticketLine.init();
		assertEquals(Integer.valueOf(0), ticketLine.getResult());




	}
	
	public void testSecondAndThirdDifferentFromTheFirst() {
		TicketLine ticketLine = new TicketLine(1, 0, 0);
		ticketLine.init();
		assertEquals(Integer.valueOf(1), ticketLine.getResult());


		ticketLine = new TicketLine(0, 2, 2);
		ticketLine.init();
		assertEquals(Integer.valueOf(1), ticketLine.getResult());

		ticketLine = new TicketLine(2, 1, 1);
		ticketLine.init();
		assertEquals(Integer.valueOf(1), ticketLine.getResult());

	}

	public void testAllTheSame() {
		TicketLine ticketLine = new TicketLine(0, 0, 0);
		ticketLine.init();
		assertEquals(Integer.valueOf(5), ticketLine.getResult());

		ticketLine = new TicketLine(1, 1, 1);
		ticketLine.init();
		assertEquals(Integer.valueOf(5), ticketLine.getResult());

		ticketLine = new TicketLine(2, 2, 2);
		ticketLine.init();
		assertEquals(Integer.valueOf(5), ticketLine.getResult());
	}

	public void testSumEqualToTwo() {
		TicketLine ticketLine = new TicketLine(1, 0, 1);
		ticketLine.init();
		assertEquals(Integer.valueOf(10), ticketLine.getResult());

		ticketLine = new TicketLine(0, 1, 1);
		ticketLine.init();

		assertEquals(Integer.valueOf(10), ticketLine.getResult());

		ticketLine = new TicketLine(1, 1, 0);
		ticketLine.init();

		assertEquals(Integer.valueOf(10), ticketLine.getResult());

		ticketLine = new TicketLine(2, 0, 0);
		ticketLine.init();

		assertEquals(Integer.valueOf(10), ticketLine.getResult());

		ticketLine = new TicketLine(0, 2, 0);
		ticketLine.init();

		assertEquals(Integer.valueOf(10), ticketLine.getResult());

		ticketLine = new TicketLine(0, 0, 2);
		ticketLine.init();

		assertEquals(Integer.valueOf(10), ticketLine.getResult());
	}

}
