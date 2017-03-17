package org.frank.bogle.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by frankbogle on 30/06/2016.
 */
@Component
public class LrqaLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(LrqaLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {

        if (authentication != null && authentication.getDetails() != null){
            try {
                logger.info("LrqaLogoutSuccessHandler invalidating HTTP session for: " + authentication.getPrincipal());
                request.getSession().invalidate();
                logger.info("LrqaLogoutSuccessHandler logout successful and complete");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.setStatus(HttpServletResponse.SC_OK);

        response.sendRedirect("/bosfaqhome");
    }
}
