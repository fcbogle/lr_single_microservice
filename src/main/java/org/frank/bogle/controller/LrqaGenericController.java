package org.frank.bogle.controller;

import org.frank.bogle.lrqamodel.LrqaAppFeedback;
import org.frank.bogle.lrqamodel.LrqaBosAnswer;
import org.frank.bogle.lrqamodel.LrqaBosQuestion;
import org.frank.bogle.lrqamodel.LrqaPerson;
import org.frank.bogle.service.LrqaAppFeedbackService;
import org.frank.bogle.service.LrqaBosAnswerService;
import org.frank.bogle.service.LrqaBosPersonService;
import org.frank.bogle.service.LrqaBosQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by frankbogle on 23/06/2016.
 */
@CrossOrigin
@RestController
public class LrqaGenericController {

    private final LrqaBosQuestionService lrqaBosQuestionService;
    private final LrqaBosAnswerService lrqaBosAnswerService;
    private final LrqaBosPersonService lrqaBosPersonService;
    private final LrqaAppFeedbackService lrqaAppFeedbackService;
    private static final Logger logger = LoggerFactory.getLogger(LrqaGenericController.class);

    @Autowired
    public LrqaGenericController(LrqaBosQuestionService lrqaBosQuestionService,
                                 LrqaBosPersonService lrqaBosPersonService,
                                 LrqaBosAnswerService lrqaBosAnswerService,
                                 LrqaAppFeedbackService lrqaAppFeedbackService){
        this.lrqaBosQuestionService = lrqaBosQuestionService;
        this.lrqaBosPersonService = lrqaBosPersonService;
        this.lrqaBosAnswerService = lrqaBosAnswerService;
        this.lrqaAppFeedbackService = lrqaAppFeedbackService;
    }

    @RequestMapping(value = "/getalllrqaquestions", method = RequestMethod.GET)
    public List<LrqaBosQuestion> getAllLrqaQuestions(){
        logger.info("LrqaGenericController method getAllLrqaQuestions() invoked: " + LocalDateTime.now());
        List<LrqaBosQuestion> lrqaQuestions = this.lrqaBosQuestionService.findAllLrqaQuestions();
        return lrqaQuestions;
    }

    @RequestMapping(value = "/getalllrqapeople", method = RequestMethod.GET)
    public List<LrqaPerson> getAllLrqaPeople(){
        logger.info("LrqaGenericController method getAllLrqaPeople() invoked: " + LocalDateTime.now());
        List<LrqaPerson> lrqaPeople = this.lrqaBosPersonService.findAllLrqaPersons();
        return lrqaPeople;
    }

    @RequestMapping(value = "/getalllrqaanswers", method = RequestMethod.GET)
    public List<LrqaBosAnswer> getAllLrqaAnswers(){
        logger.info("LrqaGenericController method getAllLrqaAnswers() invoked: " + LocalDateTime.now());
        List<LrqaBosAnswer> lrqaAnswers = this.lrqaBosAnswerService.findAllLrqaAnswers();
        return lrqaAnswers;
    }

    @RequestMapping(value = "/getalllrqafeedback", method = RequestMethod.GET)
    public List<LrqaAppFeedback> getAllLrqaFeedback(){
        logger.info("LrqaGenericController method getAllLrqaFeedback() invoked: " + LocalDateTime.now());
        List<LrqaAppFeedback> lrqaFeedback = this.lrqaAppFeedbackService.findAllLrqaFeedback();
        return lrqaFeedback;
    }


}
