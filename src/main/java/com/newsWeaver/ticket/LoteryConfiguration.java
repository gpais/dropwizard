package com.newsWeaver.ticket;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class LoteryConfiguration extends Configuration {
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
	public DataSourceFactory getDatabase() {
		return database;
	}

    @JsonProperty("database")
	public void setDatabase(DataSourceFactory database) {
		this.database = database;
	}}
