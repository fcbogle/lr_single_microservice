package org.frank.bogle.hateoas;

import org.frank.bogle.controller.LrqaPersonController;
import org.frank.bogle.lrqamodel.LrqaPerson;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by frankbogle on 11/07/2016.
 */
public class AdminPersonLink {

    private final LrqaPerson person;
    private final Link link;

    public AdminPersonLink(LrqaPerson person){
        this.person = person;
        this.link = linkTo(methodOn(LrqaPersonController.class)
                .getAdminPerson(person.getId()))
                .withRel(person.getFirstname() + " " + person.getLastname());

    }

    public LrqaPerson getPerson(){
        return person;
    }

    public Link getLink(){
        return link;
    }
}
