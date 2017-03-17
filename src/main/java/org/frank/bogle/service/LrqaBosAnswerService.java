package org.frank.bogle.service;

import org.frank.bogle.lrqamodel.LrqaBosAnswer;
import org.frank.bogle.lrqamodel.LrqaPerson;
import org.frank.bogle.repository.LrqaBosAnswerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by frankbogle on 22/06/2016.
 */
@Service
public class LrqaBosAnswerService {

    private final LrqaBosAnswerRepository lrqaBosAnswerRepository;
    private final static Logger logger = LoggerFactory.getLogger(LrqaBosAnswerService.class);

    @Autowired
    public LrqaBosAnswerService(LrqaBosAnswerRepository lrqaBosAnswerRepository){
        this.lrqaBosAnswerRepository = lrqaBosAnswerRepository;
    }

    public LrqaBosAnswer findSingleLrqaAnswer(String id){
        logger.info("LrqaBosAnswerService method findSingleLrqaAnswer() invoked: " + LocalDateTime.now());
        LrqaBosAnswer lrqaAnswer = this.lrqaBosAnswerRepository.findOne(id);
        return lrqaAnswer;
    }

    public LrqaBosAnswer findLrqaAnswerByText(String answerText){
        logger.info("LrqaBosAnswerService method findLrqaAnswerByText() invoked: " + LocalDateTime.now());
        LrqaBosAnswer lrqaAnswer = this.lrqaBosAnswerRepository.findByAnswerText(answerText);
        return lrqaAnswer;
    }

    public List<LrqaBosAnswer> findByAnswerCreator(LrqaPerson answerCreator){
        logger.info("LrqaBosAnswerService method findByAnswerCreator() invoked: " + LocalDateTime.now());
        List<LrqaBosAnswer> lrqaAnswers = this.lrqaBosAnswerRepository.findByAnswerCreator(answerCreator);
        return lrqaAnswers;
    }

    public List<LrqaBosAnswer> findAllLrqaAnswers(){
        logger.info("LrqaBosAnswerService method findAllLrqaAnswers() invoked: " + LocalDateTime.now());
        List<LrqaBosAnswer> lrqaAnswers = this.lrqaBosAnswerRepository.findAll();
        return lrqaAnswers;
    }

    public void saveLrqaBosAnswer(LrqaBosAnswer lrqaBosAnswer){
        logger.info("LrqaBosAnswerService method saveLrqaBosAnswer() invoked: " + LocalDateTime.now());
        this.lrqaBosAnswerRepository.save(lrqaBosAnswer);
    }

    public void deleteLrqaBosAnswer(LrqaBosAnswer lrqaBosAnswer){
        logger.info("LrqaBosAnswerService method deleteLrqaBosAnswer() invoked: " + LocalDateTime.now());
        this.lrqaBosAnswerRepository.delete(lrqaBosAnswer);
    }

    public void deleteAllLrqaBosAnswers(){
        logger.info("LrqaBosAnswerService method deleteALlLrqaBosAnswers() invoked: " + LocalDateTime.now());
        this.lrqaBosAnswerRepository.deleteAll();
    }


}
