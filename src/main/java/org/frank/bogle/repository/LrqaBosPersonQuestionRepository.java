package org.frank.bogle.repository;

import org.frank.bogle.lrqamodel.LrqaPersonQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by frankbogle on 25/06/2016.
 */
@Repository
public interface LrqaBosPersonQuestionRepository extends MongoRepository<LrqaPersonQuestion, String> {

    public LrqaPersonQuestion findByEmail(String email);

}
