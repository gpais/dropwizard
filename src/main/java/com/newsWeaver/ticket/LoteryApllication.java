package com.newsWeaver.ticket;


import com.newsWeaver.lotery.model.Ticket;
import com.newsWeaver.lotery.service.TicketService;
import com.newsWeaver.lotery.ticket.Resource.Controler;
import com.newsWeaver.ticket.Dao.TicketDao;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class LoteryApllication extends Application<LoteryConfiguration> {

	
    private final HibernateBundle<LoteryConfiguration> hibernateBundle =
            new HibernateBundle<LoteryConfiguration>(Ticket.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(LoteryConfiguration configuration) {
                    return configuration.getDatabase();
                }
            };
            
            
    public static void main(final String[] args) throws Exception {
        new LoteryApllication().run(args);
    }

    @Override
    public String getName() {
        return "LoteryApllication";
    }

    @Override
    public void initialize(final Bootstrap<LoteryConfiguration> bootstrap) {
    	bootstrap.addBundle(new MigrationsBundle<LoteryConfiguration>() {
    		@Override
    		public DataSourceFactory getDataSourceFactory(LoteryConfiguration configuration) {
    			return configuration.getDatabase();
    		}
    	});
        bootstrap.addBundle(hibernateBundle);
        
    }

    @Override
    public void run(final LoteryConfiguration configuration, final Environment environment) {
        environment.jersey().register(new Controler(new TicketService(new TicketDao(hibernateBundle.getSessionFactory()))));
    }

}
