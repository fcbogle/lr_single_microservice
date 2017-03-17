package org.frank.bogle.hateoas;

import org.frank.bogle.controller.LrqaQuestionController;
import org.frank.bogle.lrqamodel.LrqaBosQuestion;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by frankbogle on 30/06/2016.
 */
public class AssessorQuestionLink {

    private final LrqaBosQuestion lrqaBosQuestion;
    private final Link link;

    public AssessorQuestionLink(LrqaBosQuestion lrqaBosQuestion){
        this.lrqaBosQuestion = lrqaBosQuestion;
        this.link = linkTo(methodOn(LrqaQuestionController.class)
                .getLrqaBosQuestionOptions(lrqaBosQuestion.getId()))
                .withRel(lrqaBosQuestion.getQuestionTitle());
    }

    public LrqaBosQuestion getLrqaBosQuestion() {
        return lrqaBosQuestion;
    }

    public Link getLink() {
        return link;
    }
}
