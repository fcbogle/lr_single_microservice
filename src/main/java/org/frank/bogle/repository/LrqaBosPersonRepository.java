package org.frank.bogle.repository;

import org.frank.bogle.lrqamodel.LrqaPerson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by frankbogle on 21/06/2016.
 */
@Repository
public interface LrqaBosPersonRepository extends MongoRepository<LrqaPerson, String> {

    public LrqaPerson findByEmail(String email);

    public List<LrqaPerson> findByCountry(String country);
}
