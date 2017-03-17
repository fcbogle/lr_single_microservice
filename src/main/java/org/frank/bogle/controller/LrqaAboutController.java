package org.frank.bogle.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

/**
 * Created by frankbogle on 23/07/2016.
 */
@Controller
public class LrqaAboutController {

    private static final Logger logger = LoggerFactory.getLogger(LrqaAboutController.class);

    @RequestMapping(value = "/lrfaqappabout", method = RequestMethod.GET)
    public ModelAndView getAppAbout(){
        logger.info("LrqaAboutController method getAppLogin() invoked: " + LocalDateTime.now());
        return new ModelAndView("lrfaqappabout");
    }

}
