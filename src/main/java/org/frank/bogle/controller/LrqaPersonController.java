package org.frank.bogle.controller;

import org.frank.bogle.hateoas.AdminPersonLink;
import org.frank.bogle.lrqamodel.LrqaPerson;
import org.frank.bogle.service.LrqaBosPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by frankbogle on 11/07/2016.
 */
@Controller
public class LrqaPersonController {

    private final LrqaBosPersonService personService;
    private static final Logger logger = LoggerFactory.getLogger(LrqaPersonController.class);

    @Autowired
    public LrqaPersonController(LrqaBosPersonService personService){
        this.personService = personService;
    }

    @RequestMapping(value = "/adminallpeople", method = RequestMethod.GET)
    public ModelAndView getAdminAllPeople(){
        logger.info("RegisteredAdminController method getAdminAllPeople invoked: " + LocalDateTime.now());
        return new ModelAndView("adminallpeople")
                .addObject("people",
                        StreamSupport.stream(personService.findAllLrqaPersons()
                                .spliterator(), false).map(AdminPersonLink::new)
                                .toArray())
                .addObject("links", Arrays.asList(
                        linkTo(methodOn(LrqaHomeController.class)
                                .getAdminHome())
                                .withRel("Home")
                ));
    }

    @RequestMapping(value = "/adminperson/{id}", method = RequestMethod.GET)
    public ModelAndView getAdminPerson(@PathVariable String id){
        logger.info("RegisteredAdminController method getAdminPerson invoked: " + LocalDateTime.now());
        final LrqaPerson person = personService.findSingleLrqaPerson(id);
        return new ModelAndView("adminperson")
                .addObject("person", person)
                .addObject("links", Arrays.asList(
                        linkTo(methodOn(LrqaPersonController.class)
                                .getAdminAllPeople())
                                .withRel("All People")
                ));
    }

}
