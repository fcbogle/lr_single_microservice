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
 * Created by frankbogle on 23/06/2016.
 */
@Configuration
public class LocalMongoConfig extends AbstractMongoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(LocalMongoConfig.class);

    @Value("localhost")
    private String host;

    @Value("27017")
    private Integer port;

    @Value("bosfaq")
    private String database;

    @Value("fcbogle")
    private String username;

    @Value("take2asp1r1n")
    private String password;

    @Override
    public String getDatabaseName(){
        return database;
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        logger.info("LocalMongoConfig method mongo() invoked for database BOSFAQ: " + LocalDateTime.now());
        return new MongoClient(
                singletonList(new ServerAddress(host,port)),
                singletonList(MongoCredential.createCredential(username,
                        database, password.toCharArray()))
        );

    }

}
