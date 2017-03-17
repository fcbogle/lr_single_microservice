package org.frank.bogle.repository;

import org.frank.bogle.lrqamodel.LrqaBosQuestion;
import org.frank.bogle.lrqamodel.LrqaPerson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by frankbogle on 21/06/2016.
 */
@Repository
public interface LrqaBosQuestionRepository extends MongoRepository<LrqaBosQuestion, String> {

    public List<LrqaBosQuestion> findByQuestionCreator(LrqaPerson questionCreator);
    public LrqaBosQuestion findByQuestionText(String questionText);
}
