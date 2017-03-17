package org.frank.bogle.controller;

import org.frank.bogle.lrqamodel.LrqaPerson;
import org.frank.bogle.service.LrqaBosPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by frankbogle on 10/08/2016.
 */
@RestController
public class LrqaRestController {

    private final LrqaBosPersonService lrqaBosPersonService;
    private static final Logger logger = LoggerFactory.getLogger(LrqaRestController.class);

    @Autowired
    public LrqaRestController(LrqaBosPersonService lrqaBosPersonService){
        this.lrqaBosPersonService = lrqaBosPersonService;
    }

    @RequestMapping(value = "/allusers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LrqaPerson>> listAllUsersRest(){
        logger.info("LrqaRestService method listAllUsersRest() invoked: " + LocalDateTime.now());
        List<LrqaPerson> allusers = this.lrqaBosPersonService.findAllLrqaPersons();

        if(allusers == null){
            return new ResponseEntity<List<LrqaPerson>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<LrqaPerson>>(allusers, HttpStatus.OK);
    }

    @RequestMapping(value = "/post/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createRestUser(@RequestBody LrqaPerson lrqaPerson, UriComponentsBuilder ucBuilder){
        logger.info("LrqaRestService method createRestUser() invoked: " + LocalDateTime.now());
        this.lrqaBosPersonService.saveNewLrqaPerson(lrqaPerson);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/post/user/{id}").buildAndExpand(lrqaPerson.getId()).toUri());
        logger.info("HTTP Headers for new LrqaPerson from JSON is: " + headers.toString());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
