package org.frank.bogle.service;

import org.frank.bogle.lrqamodel.LrqaPerson;
import org.frank.bogle.repository.LrqaBosPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by frankbogle on 22/06/2016.
 */
@Service
public class LrqaBosPersonService {

    private final LrqaBosPersonRepository lrqaBosPersonRepository;
    private PasswordEncoder encoder;
    private static final Logger logger = LoggerFactory.getLogger(LrqaBosPersonService.class);

    @Autowired
    public LrqaBosPersonService(LrqaBosPersonRepository lrqaBosPersonRepository,
                                PasswordEncoder encoder){
        this.lrqaBosPersonRepository = lrqaBosPersonRepository;
        this.encoder = encoder;
    }

    public LrqaPerson findSingleLrqaPerson(String id){
        logger.info("LrqaBosPersonService method findSingleLrqaPerson() invoked: " + LocalDateTime.now());
        LrqaPerson lrqaPerson = this.lrqaBosPersonRepository.findOne(id);
        return lrqaPerson;
    }

    public LrqaPerson findLrqaPersonByEmail(String email){
        logger.info("LrqaBosPersonService method findLrqaPersonByEmail() invoked: " + LocalDateTime.now());
        LrqaPerson lrqaPerson = this.lrqaBosPersonRepository.findByEmail(email);
        return lrqaPerson;
    }

    public List<LrqaPerson> findLrqaPersonsByCountry(String country){
        logger.info("LrqaBosPersonService method findLrqaPersonsByCountry() invoked: " + LocalDateTime.now());
        List<LrqaPerson> lrqaPersons = this.lrqaBosPersonRepository.findByCountry(country);
        return lrqaPersons;
    }

    public List<LrqaPerson> findAllLrqaPersons(){
        logger.info("LrqaBosPersonService method findAllLrqaPersons() invoked: " + LocalDateTime.now());
        List<LrqaPerson> lrqaPersons = this.lrqaBosPersonRepository.findAll();
        return lrqaPersons;
    }

    public void saveNewLrqaPerson(LrqaPerson lrqaPerson){
        logger.info("LrqaBosPersonService method saveNewLrqaPerson() invoked: " + LocalDateTime.now());
        String password = encoder.encode(lrqaPerson.getPassword());
        lrqaPerson.setPassword(password);
        this.lrqaBosPersonRepository.save(lrqaPerson);
    }

    public void saveExistingLrqaPerson(LrqaPerson lrqaPerson){
        logger.info("LrqaBosPersonService method saveExistingLrqaPerson() invoked: " + LocalDateTime.now());
        this.lrqaBosPersonRepository.save(lrqaPerson);
    }

    public void deleteLrqaPerson(LrqaPerson lrqaPerson){
        logger.info("LrqaBosPersonService method deleteLrqaPerson() invoked: " + LocalDateTime.now());
        this.lrqaBosPersonRepository.delete(lrqaPerson);
    }

    public void deleteAllLrqaPersons(){
        logger.info("LrqaBosPersonService method deleteAllLrqaPersons() invoked: " + LocalDateTime.now());
        this.lrqaBosPersonRepository.deleteAll();
    }

    public String getCurrentLrqaPrincipalName(){

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            logger.info("PersonService method getCurrentPrincipal UserDetails invoked: " + username);
        } else {
            username = principal.toString();
            logger.info("PersonService method getCurrentPrincipal String invoked: " + username);
        }
        return username;
    }

}
