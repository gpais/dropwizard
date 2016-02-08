package com.newsWeaver.lotery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Ticket")
@NamedQueries({
    @NamedQuery(
            name = "com.newsWeaver.lotery.model.Ticket.findAll",
            query = "FROM Ticket t"
    )
})
public class Ticket {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "DATA", unique = false, nullable = false, length = 100000)
    private byte[] data;
    
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    
    @Transient
    private String[][] transientData;
    
	public String[][] getTransientData() {
		return transientData;
	}

	public void setTransientData(String[][] transientData) {
		this.transientData = transientData;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public long getId() {
		return id;
	}


}
