package org.frank.bogle.repository;

import org.frank.bogle.lrqamodel.LrqaPersonAnswer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by frankbogle on 25/06/2016.
 */
@Repository
public interface LrqaBosPersonAnswerRepository extends MongoRepository<LrqaPersonAnswer, String> {

    public LrqaPersonAnswer findByEmail(String email);
}
