package org.frank.bogle.service;

import org.frank.bogle.lrqamodel.LrqaPersonAnswer;
import org.frank.bogle.lrqamodel.LrqaPersonQuestion;
import org.frank.bogle.repository.LrqaBosPersonAnswerRepository;
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
public class LrqaBosPersonAnswerService {

    private LrqaBosPersonAnswerRepository lrqaBosPersonAnswerRepository;
    private final static Logger logger = LoggerFactory.getLogger(org.frank.bogle.service.LrqaBosPersonQuestionService.class);

    @Autowired
    public LrqaBosPersonAnswerService(LrqaBosPersonAnswerRepository lrqaBosPersonAnswerRepository) {
        this.lrqaBosPersonAnswerRepository = lrqaBosPersonAnswerRepository;
    }

    public LrqaPersonAnswer findSingleLrqaBosPersonAnswer(String id) {
        logger.info("LrqaBosPersonAnswerService method findSingleLrqaBosPersonAnswer() method invoked: " + LocalDateTime.now());
        LrqaPersonAnswer lrqaPersonAnswer = this.lrqaBosPersonAnswerRepository.findOne(id);
        return lrqaPersonAnswer;
    }

    public List<LrqaPersonAnswer> findAllLrqaBosPersonAnswers() {
        logger.info("LrqaBosPersonAnswerService method findAllLrqaBosPersonAnswers() method invoked: " + LocalDateTime.now());
        List<LrqaPersonAnswer> lrqaPersonAnswer = this.lrqaBosPersonAnswerRepository.findAll();
        return lrqaPersonAnswer;
    }

    public void saveLrqaPersonAnswer(LrqaPersonAnswer lrqaPersonAnswer) {
        logger.info("LrqaBosPersonAnswerService method saveLrqaBosPersonAnswer() method invoked: " + LocalDateTime.now());
        this.lrqaBosPersonAnswerRepository.save(lrqaPersonAnswer);
    }

    public LrqaPersonAnswer findByEmail(String email){
        logger.info("LrqaBosPersonAnswerService method findByEmail() method invoked: " + LocalDateTime.now());
        LrqaPersonAnswer lrqaPersonAnswer = this.lrqaBosPersonAnswerRepository.findByEmail(email);
        return lrqaPersonAnswer;
    }

    public void deleteAllBosPersonAnswers(){
        logger.info("LrqaBosPersonAnswerService method deleteAllBosPersonQuestions() method invoked: " + LocalDateTime.now());
        this.lrqaBosPersonAnswerRepository.deleteAll();
    }
}
