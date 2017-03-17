package org.frank.bogle.service;

import org.frank.bogle.lrqamodel.LrqaBosAnswer;
import org.frank.bogle.lrqamodel.LrqaBosQuestion;
import org.frank.bogle.lrqamodel.LrqaPerson;
import org.frank.bogle.repository.LrqaBosAnswerRepository;
import org.frank.bogle.repository.LrqaBosQuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frankbogle on 22/06/2016.
 */
@Service
public class LrqaBosQuestionService {

    private final LrqaBosQuestionRepository lrqaBosQuestionRepository;
    private final static Logger logger = LoggerFactory.getLogger(LrqaBosQuestionService.class);

    @Autowired
    public LrqaBosQuestionService(LrqaBosQuestionRepository lrqaBosQuestionRepository) {
        this.lrqaBosQuestionRepository = lrqaBosQuestionRepository;
    }

    public LrqaBosQuestion findSingleLrqaQuestion(String id){
        logger.info("LrqaBosQuestionService method findSingleLrqaQuestion() invoked: " + LocalDateTime.now());
        LrqaBosQuestion lrqaQuestion = this.lrqaBosQuestionRepository.findOne(id);
        return lrqaQuestion;
    }

    public LrqaBosQuestion findByQuestionText(String questionText){
        logger.info("LrqaBosQuestionService method findLrqaQuestionByText() invoked: " + LocalDateTime.now());
        LrqaBosQuestion lrqaQuestion = this.lrqaBosQuestionRepository.findByQuestionText(questionText);
        return lrqaQuestion;
    }

    public List<LrqaBosQuestion> findAllLrqaQuestionNoAnswer(){
        logger.info("LrqaBosQuestionService method findAllLrqaQuestionNoAnswer() invoked: " + LocalDateTime.now());
        List<LrqaBosQuestion> allQuestions = this.lrqaBosQuestionRepository.findAll();
        List<LrqaBosQuestion> questionsNoAnswers = new ArrayList<>();
        for(LrqaBosQuestion q : allQuestions){
            if(q.getAnswerResponders().isEmpty()){
                questionsNoAnswers.add(q);
                logger.info("There are " + questionsNoAnswers.size() + " LrqaBosQuestions with no answers");
            }
        }
        return questionsNoAnswers;
    }

    public List<LrqaBosQuestion> findAllLrqaQuestionYesAnswer(){
        logger.info("LrqaBosQuestionService method findAllLrqaQuestionYesAnswer() invoked: " + LocalDateTime.now());
        List<LrqaBosQuestion> allQuestions = this.lrqaBosQuestionRepository.findAll();
        List<LrqaBosQuestion> questionsYesAnswers = new ArrayList<>();
        for(LrqaBosQuestion q : allQuestions){
            if(!q.getAnswerResponders().isEmpty()){
                questionsYesAnswers.add(q);
                logger.info("There are " + questionsYesAnswers.size() + " LrqaBosQuestions with answers");
            }
        }
        return questionsYesAnswers;
    }

    public List<LrqaBosQuestion> findByQuestionCreator(LrqaPerson questionCreator){
        logger.info("LrqaBosQuestionService method findByQuestionCreator() invoked: " + LocalDateTime.now());
        List<LrqaBosQuestion> lrqaQuestions = this.lrqaBosQuestionRepository.findByQuestionCreator(questionCreator);
        return lrqaQuestions;
    }

    public List<LrqaBosQuestion> findAllLrqaQuestions(){
        logger.info("LrqaBosQuestionService method findAllLrgeQuestions() invoked: " + LocalDateTime.now());
        List<LrqaBosQuestion> lrqaQuestions = this.lrqaBosQuestionRepository.findAll();
        return lrqaQuestions;
    }

    public void saveLrqaBosQuestion(LrqaBosQuestion lrqaBosQuestion){
        logger.info("LrqaBosQuestionService method saveLrqaBosQuestion() invoked: " + LocalDateTime.now());
        this.lrqaBosQuestionRepository.save(lrqaBosQuestion);
    }

    public void deleteLrqaBosQuestion(LrqaBosQuestion lrqaBosQuestion){
        logger.info("LrqaBosQuestionService method deleteLrqaBosQuestion() invoked: " + LocalDateTime.now());
        this.lrqaBosQuestionRepository.delete(lrqaBosQuestion);
    }

    public void deleteAllLrqaBosQuestions(){
        logger.info("LrqaBosQuestionService method deleteAllLrqaBosQuestions() invoked: " + LocalDateTime.now());
        this.lrqaBosQuestionRepository.deleteAll();
    }

}
