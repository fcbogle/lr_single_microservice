package org.frank.bogle.repository;

import org.frank.bogle.lrqamodel.LrqaBosAnswer;
import org.frank.bogle.lrqamodel.LrqaPerson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by frankbogle on 21/06/2016.
 */
@Repository
public interface LrqaBosAnswerRepository extends MongoRepository<LrqaBosAnswer, String> {

    public List<LrqaBosAnswer> findByAnswerCreator(LrqaPerson answerCreator);

    public LrqaBosAnswer findByAnswerText(String answerText);
}
