package org.frank.bogle.dbconfig;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import java.time.LocalDateTime;

import static java.util.Collections.singletonList;

/**
 * Created by frankbogle on 12/07/2016.
 */

public class MongoLabConfig extends AbstractMongoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(org.frank.bogle.dbconfig.MongoLabConfig.class);

    @Value("ds031948.mlab.com")
    private String host;

    @Value("31948")
    private Integer port;

    @Value("CloudFoundry_ieo2l8m4_18esj4ed")
    private String database;

    @Value("fcbogle")
    private String username;

    @Value("take2asp1r1n")
    private String password;

    @Override
    public String getDatabaseName() {
        return database;
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        logger.info("MongoLabConfig method mongo() invoked: " + LocalDateTime.now());
        return new MongoClient(
                singletonList(new ServerAddress(host, port)),
                singletonList(MongoCredential.createCredential(username,
                        database, password.toCharArray()))
        );

    }
}
