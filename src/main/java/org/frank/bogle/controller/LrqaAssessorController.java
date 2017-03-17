package org.frank.bogle.controller;

import org.frank.bogle.hateoas.AssessorQuestionLink;
import org.frank.bogle.lrqamodel.LrqaBosAnswer;
import org.frank.bogle.lrqamodel.LrqaBosQuestion;
import org.frank.bogle.lrqamodel.LrqaPerson;
import org.frank.bogle.lrqamodel.LrqaPersonQuestion;
import org.frank.bogle.service.LrqaBosPersonQuestionService;
import org.frank.bogle.service.LrqaBosPersonService;
import org.frank.bogle.service.LrqaBosQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by frankbogle on 30/06/2016.
 */
@Controller
public class LrqaAssessorController {

    private final LrqaBosPersonService personService;
    private final LrqaBosQuestionService questionService;
    private final LrqaBosPersonQuestionService personQuestionService;
    private static final Logger logger = LoggerFactory.getLogger(LrqaAssessorController.class);

    @Autowired
    public LrqaAssessorController(LrqaBosPersonService personService,
                                  LrqaBosQuestionService questionService,
                                  LrqaBosPersonQuestionService personQuestionService) {
        this.personService = personService;
        this.questionService = questionService;
        this.personQuestionService = personQuestionService;
    }

    @RequestMapping(value = "/goassessorhome", method = RequestMethod.GET)
    public ModelAndView goAssessorHome(){
        logger.info("LrqaAssessorController method getAssessorHome() invoked: " + LocalDateTime.now());
        String assessorname = this.personService.getCurrentLrqaPrincipalName();
        logger.info("LrqaAssessorController Current Principal username is: " + assessorname);
        LrqaPerson lrqaPerson = this.personService.findLrqaPersonByEmail(assessorname);
        return new ModelAndView("bosassessorhome")
                .addObject("lrqaPerson", lrqaPerson)
                .addObject("assessorViewAskQuestionLink",
                        linkTo(methodOn(LrqaAssessorController.class)
                                .askNewLrqaQuestion(lrqaPerson.getId()))
                                .withSelfRel())
                .addObject("assessorViewAllQuestionsLink",
                        linkTo(methodOn(LrqaQuestionController.class)
                                .getAllAnsweredLrqaBosQuestions())
                                .withSelfRel())
                .addObject("links", Arrays.asList(
                        linkTo(methodOn(LrqaHomeController.class)
                                .getAppHome())
                                .withRel("Cancel")
                ));

    }

    @RequestMapping(value = "/getmyanswers", method = RequestMethod.GET)
    public ModelAndView getMyAnswers(){
        logger.info("LrqaAssessorController method getMyAnswers() invoked: " + LocalDateTime.now());
        String assessorname = this.personService.getCurrentLrqaPrincipalName();
        logger.info("LrqaAssessorController Current Principal username is: " + assessorname);
        LrqaPerson lrqaPerson = this.personService.findLrqaPersonByEmail(assessorname);
        List<LrqaBosQuestion> myQuestions = lrqaPerson.getSubmittedQuestions();

        logger.info("Answers to my questions: " + myQuestions.toString());

        return new ModelAndView("assessoranswers")
                .addObject("myQuestions", myQuestions)
                .addObject("lrqaPerson", lrqaPerson)
                .addObject("links", Arrays.asList(
                        linkTo(methodOn(LrqaAssessorController.class)
                                .goAssessorHome())
                                .withRel("Cancel")
                ));

    }

    @RequestMapping(value = "/getansweredquestions", method = RequestMethod.GET)
    public ModelAndView getAnsweredQuestions(){
        logger.info("LrqaAssessorController method getAnsweredQuestions() invoked: " + LocalDateTime.now());
        String assessorname = this.personService.getCurrentLrqaPrincipalName();
        logger.info("LrqaAssessorController Current Principal username is: " + assessorname);
        LrqaPerson lrqaPerson = this.personService.findLrqaPersonByEmail(assessorname);
        List<LrqaBosQuestion> answeredQuestions = this.questionService.findAllLrqaQuestionYesAnswer();

        logger.info("Answers to all questions: " + answeredQuestions.toString());

        return new ModelAndView("answeredquestions")
                .addObject("lrqaPerson", lrqaPerson)
                .addObject("answeredQuestions", answeredQuestions)
                .addObject("links", Arrays.asList(
                        linkTo(methodOn(LrqaAssessorController.class)
                                .goAssessorHome())
                                .withRel("Cancel")
                ));
    }

    @RequestMapping(value = "/asklrqaquestion/{id}/askquestion", method = RequestMethod.GET)
    public ModelAndView askNewLrqaQuestion(@PathVariable String id) {
        logger.info("LrqaAssessorController method askNewLrqaQuestion() invoked: " + LocalDateTime.now());
        final LrqaBosQuestion lrqaBosQuestion = new LrqaBosQuestion();
        final LrqaPerson lrqaPerson = this.personService.findSingleLrqaPerson(id);
        return new ModelAndView("lrqabosquestion")
                .addObject("lrqaBosQuestion", lrqaBosQuestion)
                .addObject("lrqaPerson", lrqaPerson)
                .addObject("links", Arrays.asList(
                        linkTo(methodOn(LrqaAssessorController.class)
                                .goAssessorHome())
                                .withRel("Cancel")
                ));
    }

    @RequestMapping(value = "/asklrqaquestion", method = RequestMethod.POST)
    public ModelAndView createNewLrqaQuestion(@ModelAttribute LrqaBosQuestion lrqaBosQuestion){
        logger.info("LrqaAssessorController method createNewLrqaQuestion() invoked: " + LocalDateTime.now());
        String principal = this.personService.getCurrentLrqaPrincipalName();
        logger.info("Found LRQA Principal: " + principal);

        String questionText = lrqaBosQuestion.getQuestionText();
        logger.info("Submitted Question Text is: " + questionText);

        //save the submiited question to mongodb
        this.questionService.saveLrqaBosQuestion(lrqaBosQuestion);

        //find LrqaPerson object based on principal username
        LrqaPerson lrqaPerson = this.personService.findLrqaPersonByEmail(principal);


        LrqaPersonQuestion lrqaPersonQuestion = new LrqaPersonQuestion();
        lrqaPersonQuestion.setCountry(lrqaPerson.getCountry());
        lrqaPersonQuestion.setFirstname(lrqaPerson.getFirstname());
        lrqaPersonQuestion.setLastname(lrqaPerson.getLastname());
        lrqaPersonQuestion.setEmail(lrqaPerson.getEmail());
        lrqaPersonQuestion.setQuestionPersonId(lrqaPerson.getId());

        //save details of person submitting questions
        this.personQuestionService.saveLrqaPersonQuestion(lrqaPersonQuestion);

        //obtain the submitter from mongodb
        LrqaPersonQuestion submitter = this.personQuestionService.findByEmail(principal);
        logger.info("LrqaPeronQuestion submitter is: " + submitter);

        List<LrqaBosQuestion> currentQuestions = lrqaPerson.getSubmittedQuestions();


        //get the submitted question back from the database crucially including id
        //update the question with creator details and save back to mongodb
        LrqaBosQuestion retrievedLrqaQuestion = this.questionService.findByQuestionText(questionText);
        logger.info("Retrieved Question is: " + retrievedLrqaQuestion);
        retrievedLrqaQuestion.setQuestionCreator(submitter);

        //update the principal users list with the current question
        currentQuestions.add(retrievedLrqaQuestion);
        this.questionService.saveLrqaBosQuestion(retrievedLrqaQuestion);

        lrqaPerson.setSubmittedQuestions(currentQuestions);

        //save the person including all submitted questions
        this.personService.saveExistingLrqaPerson(lrqaPerson);
        return goAssessorHome();

    }


}

