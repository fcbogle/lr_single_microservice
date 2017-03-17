package org.frank.bogle.repository;

import org.frank.bogle.lrqamodel.LrqaAppFeedback;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by frankbogle on 22/07/2016.
 */
public interface LrqaFeedbackRepository extends MongoRepository<LrqaAppFeedback, String> {

    public LrqaAppFeedback findByEmail(String email);
}
