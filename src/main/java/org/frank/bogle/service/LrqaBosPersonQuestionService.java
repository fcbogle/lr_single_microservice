package org.frank.bogle.service;

import org.frank.bogle.lrqamodel.LrqaPersonQuestion;
import org.frank.bogle.repository.LrqaBosPersonQuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by frankbogle on 25/06/2016.
 */
@Service
public class LrqaBosPersonQuestionService {

    private LrqaBosPersonQuestionRepository lrqaBosPersonQuestionRepository;
    private final static Logger logger = LoggerFactory.getLogger(LrqaBosPersonQuestionService.class);

    @Autowired
    public LrqaBosPersonQuestionService(LrqaBosPersonQuestionRepository lrqaBosPersonQuestionRepository){
        this.lrqaBosPersonQuestionRepository = lrqaBosPersonQuestionRepository;
    }

    public LrqaPersonQuestion findSingleLrqaBosPersonQuestion(String id){
        logger.info("LrqaBosPersonQuestionService method findSingleLrqaBosPersonQuestion() method invoked: " + LocalDateTime.now());
        LrqaPersonQuestion lrqaPersonQuestion = this.lrqaBosPersonQuestionRepository.findOne(id);
        return lrqaPersonQuestion;
    }

    public List<LrqaPersonQuestion> findAllLrqaBosPersonQuestions(){
        logger.info("LrqaBosPersonQuestionService method findAllLrqaBosPersonQuestions() method invoked: " + LocalDateTime.now());
        List<LrqaPersonQuestion> lrqaPersonQuestion = this.lrqaBosPersonQuestionRepository.findAll();
        return lrqaPersonQuestion;
    }

    public void saveLrqaPersonQuestion(LrqaPersonQuestion lrqaPersonQuestion){
        logger.info("LrqaBosPersonQuestionService method saveLrqaBosPersonQuestion() method invoked: " + LocalDateTime.now());
        this.lrqaBosPersonQuestionRepository.save(lrqaPersonQuestion);
    }

    public LrqaPersonQuestion findByEmail(String email){
        logger.info("LrqaBosPersonQuestionService method findByEmail() method invoked: " + LocalDateTime.now());
        LrqaPersonQuestion lrqaPersonQuestion = this.lrqaBosPersonQuestionRepository.findByEmail(email);
        return lrqaPersonQuestion;
    }

    public void deleteAllBosPersonQuestions(){
        logger.info("LrqaBosPersonQuestionService method deleteAllBosPersonQuestions() method invoked: " + LocalDateTime.now());
        this.lrqaBosPersonQuestionRepository.deleteAll();
    }
}
