package org.frank.bogle.baseline;

import org.frank.bogle.lrqamodel.LrqaBosAnswer;
import org.frank.bogle.lrqamodel.LrqaBosQuestion;
import org.frank.bogle.lrqamodel.LrqaPerson;
import org.frank.bogle.repository.LrqaBosAnswerRepository;
import org.frank.bogle.repository.LrqaBosPersonRepository;
import org.frank.bogle.repository.LrqaBosQuestionRepository;
import org.frank.bogle.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

/**
 * Created by frankbogle on 23/06/2016.
 */

/*
@Service
public class InitialLoader {

    private final LrqaBosPersonService lrqaBosPersonService;
    private final LrqaBosPersonQuestionService lrqaBosPersonQuestionService;
    private final LrqaBosPersonAnswerService lrqaBosPersonAnswerService;
    private final LrqaBosQuestionService lrqaBosQuestionService;
    private final LrqaBosAnswerService lrqaBosAnswerService;
    private final LrqaAppFeedbackService lrqaAppFeedbackService;
    private final Logger logger = LoggerFactory.getLogger(InitialLoader.class);

    @Autowired
    public InitialLoader(LrqaBosPersonService lrqaBosPersonService,
                         LrqaBosAnswerService lrqaBosAnswerService,
                         LrqaBosQuestionService lrqaBosQuestionService,
                         LrqaBosPersonQuestionService lrqaBosPersonQuestionService,
                         LrqaBosPersonAnswerService lrqaBosPersonAnswerService,
                         LrqaAppFeedbackService lrqaAppFeedbackService){
        this.lrqaBosPersonService = lrqaBosPersonService;
        this.lrqaBosQuestionService = lrqaBosQuestionService;
        this.lrqaBosAnswerService = lrqaBosAnswerService;
        this.lrqaBosPersonQuestionService = lrqaBosPersonQuestionService;
        this.lrqaBosPersonAnswerService = lrqaBosPersonAnswerService;
        this.lrqaAppFeedbackService = lrqaAppFeedbackService;
    }

    @PostConstruct
    public void init(){
        logger.info("INFO::InitialLoader Clearing Out MongoDB, Starting Application & Creating Assessor and Admin Accounts:: " + LocalDateTime.now());

        this.lrqaBosPersonService.deleteAllLrqaPersons();
        this.lrqaBosQuestionService.deleteAllLrqaBosQuestions();
        this.lrqaBosAnswerService.deleteAllLrqaBosAnswers();
        this.lrqaBosPersonQuestionService.deleteAllBosPersonQuestions();
        this.lrqaBosPersonAnswerService.deleteAllBosPersonAnswers();
        this.lrqaAppFeedbackService.deleteAllLrqaFeedback();

        try {
            LrqaPerson lrqaPerson = new LrqaPerson();
            lrqaPerson.setFirstname("Frank");
            lrqaPerson.setLastname("Bogle");
            lrqaPerson.setCountry("Scotland");
            lrqaPerson.setEmail("frank.bogle@lr.org");
            lrqaPerson.setPassword("password");
            lrqaPerson.setAuthority("admin");

            this.lrqaBosPersonService.saveNewLrqaPerson(lrqaPerson);
        } catch(Exception e){
            e.printStackTrace();
        }

        try {
            LrqaPerson lrqaPerson = new LrqaPerson();
            lrqaPerson.setFirstname("Jakko");
            lrqaPerson.setLastname("DeJong");
            lrqaPerson.setCountry("Netherlands");
            lrqaPerson.setEmail("jakko.dejong@lr.org");
            lrqaPerson.setPassword("password");
            lrqaPerson.setAuthority("admin");

            this.lrqaBosPersonService.saveNewLrqaPerson(lrqaPerson);
        } catch(Exception e){
            e.printStackTrace();
        }

        try {
            LrqaPerson lrqaPerson = new LrqaPerson();
            lrqaPerson.setFirstname("Will");
            lrqaPerson.setLastname("Butcher");
            lrqaPerson.setCountry("United Kingdom");
            lrqaPerson.setEmail("will.butcher@lr.org");
            lrqaPerson.setPassword("password");
            lrqaPerson.setAuthority("user");

            this.lrqaBosPersonService.saveNewLrqaPerson(lrqaPerson);
        } catch(Exception e){
            e.printStackTrace();
        }

        try {
            LrqaPerson lrqaPerson = new LrqaPerson();
            lrqaPerson.setFirstname("Tim");
            lrqaPerson.setLastname("Warburton");
            lrqaPerson.setCountry("Wales");
            lrqaPerson.setEmail("tim.warburton@lr.org");
            lrqaPerson.setPassword("password");
            lrqaPerson.setAuthority("user");

            this.lrqaBosPersonService.saveNewLrqaPerson(lrqaPerson);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}

*/
