package org.frank.bogle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by frankbogle on 21/06/2016.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
