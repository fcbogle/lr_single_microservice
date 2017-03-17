package org.frank.bogle.controller;

import org.frank.bogle.lrqamodel.*;
import org.frank.bogle.service.*;
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
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by frankbogle on 03/07/2016.
 */
@Controller
public class LrqaAnswerController {

    private final LrqaBosPersonService personService;
    private final LrqaBosAnswerService answerService;
    private final LrqaBosPersonAnswerService personAnswerService;
    private final LrqaBosQuestionService questionService;
    private static final Logger logger = LoggerFactory.getLogger(LrqaAnswerController.class);

    @Autowired
    public LrqaAnswerController(LrqaBosPersonService personService,
                               LrqaBosAnswerService answerService,
                               LrqaBosPersonAnswerService personAnswerService,
                                LrqaBosQuestionService questionService) {
        this.personService = personService;
        this.answerService = answerService;
        this.personAnswerService = personAnswerService;
        this.questionService = questionService;
    }

    @RequestMapping(value = "/bosadminhomepage", method = RequestMethod.GET)
    public ModelAndView getAdminHomePage(){
        logger.info("LrqaHomeController method getAdminHome() invoked: " + LocalDateTime.now());
        String adminname = this.personService.getCurrentLrqaPrincipalName();
        LrqaPerson lrqaAdmin = this.personService.findLrqaPersonByEmail(adminname);
        return new ModelAndView("bosadminhome")
                .addObject("lrqaAdmin", lrqaAdmin);
    }

    @RequestMapping(value = "/lrqabosanswer/{id}", method = RequestMethod.GET)
    public ModelAndView getBosAnswer(@PathVariable String id){
        logger.info("LrqaAnswerController method getBosAnswer() invoked: " + LocalDateTime.now());
        String answername = this.personService.getCurrentLrqaPrincipalName();
        logger.info("LrqaAssessorController Current Principal username is: " + answername);
        LrqaPerson lrqaAnswerPerson = this.personService.findLrqaPersonByEmail(answername);
        LrqaBosQuestion lrqaBosQuestion = this.questionService.findSingleLrqaQuestion(id);
        LrqaBosAnswer lrqaBosAnswer = new LrqaBosAnswer();
        return new ModelAndView("adminbosanswer")
                .addObject("lrqaAnswerPerson", lrqaAnswerPerson)
                .addObject("lrqaBosQuestion", lrqaBosQuestion)
                .addObject("lrqaBosAnswer", lrqaBosAnswer)
                .addObject("adminPersonAnswerPostLink",
                        linkTo(methodOn(LrqaAnswerController.class)
                                .createBosAnswer(id, lrqaBosAnswer))
                                .withSelfRel())
                .addObject("links", Arrays.asList(
                        linkTo(methodOn(LrqaHomeController.class)
                                .getAdminHome())
                                .withRel("Cancel")
                ));
    }

    @RequestMapping(value = "/lrbosanswer/{id}/create", method = RequestMethod.POST)
    public ModelAndView createBosAnswer(@PathVariable String id, LrqaBosAnswer lrqaBosAnswer){
        logger.info("LrqaAnswerController method createBosAnswer() invoked: " + LocalDateTime.now());
        String adminname = this.personService.getCurrentLrqaPrincipalName();
        String answerText = lrqaBosAnswer.getAnswerText();

        logger.info("Current Admin Principal is: " + adminname);
        LrqaPerson lrqaPerson = this.personService.findLrqaPersonByEmail(adminname);
        logger.info("LrqaPerson creating the answer is: " + lrqaPerson);
        LrqaBosQuestion lrqaBosQuestion = this.questionService.findSingleLrqaQuestion(id);


        LrqaBosAnswer newAnswer = new LrqaBosAnswer();
        newAnswer.setAnswerText(lrqaBosAnswer.getAnswerText());
        newAnswer.setQuestionText(lrqaBosQuestion.getQuestionText());

        logger.info("Retreived question being answered is: " + lrqaBosQuestion);


        LrqaPersonAnswer lrqaPersonAnswer = new LrqaPersonAnswer();
        lrqaPersonAnswer.setCountry(lrqaPerson.getCountry());
        lrqaPersonAnswer.setFirstname(lrqaPerson.getFirstname());
        lrqaPersonAnswer.setLastname(lrqaPerson.getLastname());
        lrqaPersonAnswer.setEmail(lrqaPerson.getEmail());
        lrqaPersonAnswer.setAnswerPersonId(lrqaPerson.getId());

        this.personAnswerService.saveLrqaPersonAnswer(lrqaPersonAnswer);

        LrqaPersonAnswer lrqapersans = this.personAnswerService.findByEmail(adminname);

        newAnswer.setAnswerCreator(lrqapersans);
        this.answerService.saveLrqaBosAnswer(newAnswer);


        LrqaBosAnswer answer = this.answerService.findLrqaAnswerByText(lrqaBosAnswer.getAnswerText());
        List<LrqaBosAnswer> lrqaBosAnswers = lrqaBosQuestion.getAnswerResponders();
        lrqaBosAnswers.add(answer);

        lrqaBosQuestion.setAnswerResponders(lrqaBosAnswers);
        this.questionService.saveLrqaBosQuestion(lrqaBosQuestion);

        return getAdminHomePage();

    }
}
