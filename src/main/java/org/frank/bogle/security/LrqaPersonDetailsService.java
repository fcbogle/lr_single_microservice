package org.frank.bogle.security;

import org.frank.bogle.lrqamodel.LrqaPerson;
import org.frank.bogle.service.LrqaBosPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by frankbogle on 01/07/2016.
 */
@Service("personAuthenticationService")
public class LrqaPersonDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(LrqaPersonDetailsService.class);

    private List<GrantedAuthority> auth;

    @Autowired
    LrqaBosPersonService personService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        logger.info("LrqaPersonDetailsService loadUserByUsername() invoked: " + LocalDateTime.now());
        LrqaPerson person = personService.findLrqaPersonByEmail(email);

        if(person == null) {
            throw new UsernameNotFoundException("Invalid Email Username");
        }

        if(person.getAuthority().equals("user")){
            logger.info("LrqaPersonDetailsService authority privilege detected: " + person.getAuthority());
            auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        }

        if(person.getAuthority().equals("admin")){
            logger.info("LrqaPersonDetailsService authority privilege detected: " + person.getAuthority());
            auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN, ROLE_USER");
        }

        return new org.springframework.security.core.userdetails.User(
                email, person.getPassword(), auth );

    }

}
