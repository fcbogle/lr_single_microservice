package org.frank.bogle.service;

import org.frank.bogle.lrqamodel.LrqaAppFeedback;
import org.frank.bogle.lrqamodel.LrqaBosAnswer;
import org.frank.bogle.repository.LrqaBosAnswerRepository;
import org.frank.bogle.repository.LrqaFeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by frankbogle on 22/07/2016.
 */
@Service
public class LrqaAppFeedbackService {

    private final LrqaFeedbackRepository lrqaFeedbackRepository;
    private final static Logger logger = LoggerFactory.getLogger(LrqaAppFeedbackService.class);

    @Autowired
    public LrqaAppFeedbackService(LrqaFeedbackRepository lrqaFeedbackRepository){
        this.lrqaFeedbackRepository = lrqaFeedbackRepository;
    }

    public void saveLrqaFeedback(LrqaAppFeedback lrqaAppFeedback){
        logger.info("LrqaAppFeedbackService method saveLrqaFeedback() invoked: " + LocalDateTime.now());
        this.lrqaFeedbackRepository.save(lrqaAppFeedback);
    }

    public List<LrqaAppFeedback> findAllLrqaFeedback(){
        logger.info("LrqaAppFeedbackService method findAllLrqaFeedback() invoked: " + LocalDateTime.now());
        List<LrqaAppFeedback> lrqaFeedback = this.lrqaFeedbackRepository.findAll();
        return lrqaFeedback;
    }

    public void deleteAllLrqaFeedback(){
        logger.info("LrqaAppFeedbackService method deleteAllLrqaFeedback() invoked: " + LocalDateTime.now());
        this.lrqaFeedbackRepository.deleteAll();
    }


}
