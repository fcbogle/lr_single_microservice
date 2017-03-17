package org.frank.bogle.controller;

import org.frank.bogle.lrqamodel.LrqaAppFeedback;
import org.frank.bogle.service.LrqaAppFeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by frankbogle on 22/07/2016.
 */
@Controller
public class LrqaFeedbackController {

    private final LrqaAppFeedbackService lrqaAppFeedbackService;
    private static final Logger logger = LoggerFactory.getLogger(LrqaFeedbackController.class);

    @Autowired
    public LrqaFeedbackController(LrqaAppFeedbackService lrqaAppFeedbackService){
        this.lrqaAppFeedbackService = lrqaAppFeedbackService;
    }

    @RequestMapping(value = "/lrqahome", method = RequestMethod.GET)
    public ModelAndView getHome(){
        logger.info("LrqaHomeController method getHome() invoked: " + LocalDateTime.now());
        return new ModelAndView("bosfaqhome");
    }

    @RequestMapping(value = "/lrqaappfeedback", method = RequestMethod.GET)
    public ModelAndView getLrqaFeedbackPage(){
        logger.info("LrqaFeedbackController method getLrqaFeedbackPage() invoked: " + LocalDateTime.now());
        return new ModelAndView("lrqafeedback")
                .addObject("lrqaFeedback", new LrqaAppFeedback())
                .addObject("links", Arrays.asList(
                        linkTo(methodOn(LrqaFeedbackController.class)
                                .getHome())
                                .withRel("Cancel")
                ));
    }

    @RequestMapping(value = "/lrqaappfeedback", method = RequestMethod.POST)
    public ModelAndView postLrqaFeedback(@ModelAttribute LrqaAppFeedback lrqaFeedback){
        logger.info("LrqaFeedbackController method postLrqaFeedback() invoked: " + LocalDateTime.now());
        this.lrqaAppFeedbackService.saveLrqaFeedback(lrqaFeedback);
        return getHome();
    }
}
