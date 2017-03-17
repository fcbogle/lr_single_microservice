package org.frank.bogle.controller;

import org.frank.bogle.hateoas.AssessorQuestionLink;
import org.frank.bogle.lrqamodel.LrqaBosQuestion;
import org.frank.bogle.lrqamodel.LrqaPerson;
import org.frank.bogle.service.LrqaBosPersonService;
import org.frank.bogle.service.LrqaBosQuestionService;
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
 * Created by frankbogle on 30/06/2016.
 */
@Controller
public class LrqaQuestionController {

    private final LrqaBosQuestionService questionService;
    private final LrqaBosPersonService personService;
    private static final Logger logger = LoggerFactory.getLogger(LrqaQuestionController.class);

    @Autowired
    public LrqaQuestionController(LrqaBosQuestionService questionService,
                                  LrqaBosPersonService personService){
        this.questionService = questionService;
        this.personService = personService;
    }

    @RequestMapping(value = "/answeredlrqaquestions", method = RequestMethod.GET)
    public ModelAndView getAllAnsweredLrqaBosQuestions() {
        logger.info("LrqaQuestionController method getAllLrqaBosQuestions invoked: " + LocalDateTime.now());
        return new ModelAndView("adminallquestion")
                .addObject("questions",
                        StreamSupport.stream(this.questionService.findAllLrqaQuestionYesAnswer()
                                .spliterator(), false).map(AssessorQuestionLink::new)
                                .toArray())
                .addObject("links", Arrays.asList(
                        linkTo(methodOn(LrqaHomeController.class)
                                .getAdminHome())
                                .withRel("Cancel")
                ));
    }

    @RequestMapping(value = "/unansweredlrqaquestions", method = RequestMethod.GET)
    public ModelAndView getAllUnansweredLrqaBosQuestions() {
        logger.info("LrqaQuestionController method getAllLrqaBosQuestions invoked: " + LocalDateTime.now());
        return new ModelAndView("adminallquestion")
                .addObject("questions",
                        StreamSupport.stream(this.questionService.findAllLrqaQuestionNoAnswer()
                                .spliterator(), false).map(AssessorQuestionLink::new)
                                .toArray())
                .addObject("links", Arrays.asList(
                        linkTo(methodOn(LrqaHomeController.class)
                                .getAdminHome())
                                .withRel("Cancel")
                ));
    }

    @RequestMapping(value = "/lrqabosquestion/{id}", method = RequestMethod.GET)
    public ModelAndView getLrqaBosQuestionOptions(@PathVariable String id){
        logger.info("LrqaQuestionController method getLrqaBosQuestion invoked: " + LocalDateTime.now());
        String adminname = this.personService.getCurrentLrqaPrincipalName();
        final LrqaBosQuestion lrqabosquestion = questionService.findSingleLrqaQuestion(id);
        LrqaPerson lrqaAdmin = this.personService.findLrqaPersonByEmail(adminname);
        return new ModelAndView("lrqabosquestionoptions")
                .addObject("lrqaAdmin", lrqaAdmin)
                .addObject("lrqabosquestion", lrqabosquestion)
                .addObject("adminBosQuestionAnswerLink",
                        linkTo(methodOn(LrqaAnswerController.class)
                        .getBosAnswer(lrqabosquestion.getId()))
                        .withSelfRel())
                .addObject("links", Arrays.asList(
                        linkTo(methodOn(LrqaHomeController.class)
                                .getAdminHome())
                                .withRel("Cancel")
                ));
    }

}
