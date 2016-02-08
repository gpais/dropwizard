package com.newsWeaver.ticket.Dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.newsWeaver.lotery.model.Ticket;

import io.dropwizard.hibernate.AbstractDAO;

public class TicketDao  extends AbstractDAO<Ticket>{

	public TicketDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	

    public Ticket findById(Long id) {
        return get(id);
    }

    public long create(Ticket ticket) {
        return persist(ticket).getId();
    }
    
    public long save(Ticket ticket) {
        return persist(ticket).getId();
    }
    
    
    
    public List<Ticket> findAll() {
        return list(namedQuery("com.newsWeaver.lotery.model.Ticket.findAll"));
    }
}
