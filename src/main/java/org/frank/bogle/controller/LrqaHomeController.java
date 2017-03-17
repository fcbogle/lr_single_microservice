package org.frank.bogle.controller;

import org.frank.bogle.lrqamodel.LrqaPerson;
import org.frank.bogle.service.LrqaBosPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by frankbogle on 25/06/2016.
 */
@Controller
public class LrqaHomeController {

    private static final Logger logger = LoggerFactory.getLogger(LrqaHomeController.class);
    private final LrqaBosPersonService personService;

    @Autowired
    public LrqaHomeController(LrqaBosPersonService personService){
        this.personService = personService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getAppHome(){
        logger.info("LrqaHomeController method getAppHome() invoked: " + LocalDateTime.now());
        return new ModelAndView("bosfaqhome");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getAppLogin(){
        logger.info("LrqaHomeController method getAppLogin() invoked: " + LocalDateTime.now());
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/bosfaqhome", method = RequestMethod.GET)
    public ModelAndView getAppHomePage(){
        logger.info("LrqaHomeController method getAppHomePage() invoked: " + LocalDateTime.now());
        return new ModelAndView("bosfaqhome");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView createNewRegistration(){
        logger.info("LrqaHomeController method createNewRegistration() invoked: " + LocalDateTime.now());
        return new ModelAndView("register")
                .addObject("lrqaPerson", new LrqaPerson())
                .addObject("links", Arrays.asList(
                        linkTo(methodOn(LrqaHomeController.class)
                                .getAppHomePage())
                                .withRel("Cancel")
                ));

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView saveNewLrqaRegistration(@ModelAttribute LrqaPerson lrqaPerson){
        logger.info("LrqaHomeController method saveNewLrqaRegistration() invoked: " + LocalDateTime.now());
        this.personService.saveNewLrqaPerson(lrqaPerson);
        return getAppHome();
    }

    @RequestMapping(value = "/bosassessorhome", method = RequestMethod.GET)
    public ModelAndView getAssessorHome(){
        logger.info("LrqaHomeController method getAssessorHome() invoked: " + LocalDateTime.now());
        String assessorname = this.personService.getCurrentLrqaPrincipalName();
        logger.info("LrqaHomeController Current Principal username is: " + assessorname);
        LrqaPerson lrqaPerson = this.personService.findLrqaPersonByEmail(assessorname);
        return new ModelAndView("bosassessorhome")
                .addObject("lrqaPerson", lrqaPerson)
                .addObject("assessorViewAllQuestionsLink",
                        linkTo(methodOn(LrqaQuestionController.class)
                        .getAllAnsweredLrqaBosQuestions())
                        .withSelfRel())
                .addObject("assessorViewAskQuestionLink",
                        linkTo(methodOn(LrqaAssessorController.class)
                        .askNewLrqaQuestion(lrqaPerson.getId()))
                        .withSelfRel());

    }

    @RequestMapping(value = "/bosadminhome", method = RequestMethod.GET)
    public ModelAndView getAdminHome(){
        logger.info("LrqaHomeController method getAdminHome() invoked: " + LocalDateTime.now());
        String adminname = this.personService.getCurrentLrqaPrincipalName();
        LrqaPerson lrqaAdmin = this.personService.findLrqaPersonByEmail(adminname);
        return new ModelAndView("bosadminhome")
                .addObject("lrqaAdmin", lrqaAdmin);
    }
}
