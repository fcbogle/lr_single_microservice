package org.frank.bogle.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by frankbogle on 30/06/2016.
 */
@Component
public class LrqaLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final static Logger logger = LoggerFactory.getLogger(LrqaLoginSuccessHandler.class);
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) throws IOException {

        String targetURL = determineTargetURL(authentication);

        if(response.isCommitted()){
            logger.info("LrqaLoginSuccessHandler Cannot redirect, response already committed");
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetURL);

    }

    public String determineTargetURL(Authentication authentication){

        String URL = "";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = new ArrayList<>();

        for(GrantedAuthority a : authorities){
            roles.add(a.getAuthority());
        }

        if(isUser(roles)){
            logger.info("LrqaLoginSuccessHandler detected ROLE_USER  for " + authentication.getPrincipal());

            URL = "/bosassessorhome";
        }

        if(isAdmin(roles)){
            logger.info("LrqaLoginSuccessHandler detected ROLE_ADMIN  for " + authentication.getPrincipal());

            URL = "/bosadminhome";
        }

        return URL;
    }

    private boolean isUser(List<String> roles){
        if(roles.contains("ROLE_USER")){
            return true;
        }
        return false;
    }

    private boolean isAdmin(List<String> roles){
        if(roles.contains("ROLE_ADMIN")){
            return true;
        }
        return false;
    }
}
