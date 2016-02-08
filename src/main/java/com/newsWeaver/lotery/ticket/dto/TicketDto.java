package com.newsWeaver.lotery.ticket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.newsWeaver.lotery.model.TicketStatus;

public class TicketDto {
	
	private String[][] loteryNumbers;
	private Long id;
    private TicketStatus ticketStatus;
	
    @JsonProperty
	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}

    @JsonProperty
	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public TicketDto (){
	}
	
	public TicketDto (int numberOfLines){
		setLoteryNumbers(new String [numberOfLines][3]);
	}

    @JsonProperty
	public String[][] getLoteryNumbers() {
		return loteryNumbers;
	}

    @JsonProperty
	public void setLoteryNumbers(String[][] loteryNumbers) {
		this.loteryNumbers = loteryNumbers;
	}

    @JsonProperty
	public Long getId() {
		return id;
	}

    @JsonProperty
	public void setId(long id) {
		this.id = id;
	}
	
	

}
