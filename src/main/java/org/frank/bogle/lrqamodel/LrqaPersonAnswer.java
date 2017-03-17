package org.frank.bogle.lrqamodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by frankbogle on 25/06/2016.
 */
@Document
public class LrqaPersonAnswer {

    @Id
    private String id;

    private String firstname;
    private String lastname;
    private String email;
    private String country;
    private String answerPersonId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAnswerPersonId() {
        return answerPersonId;
    }

    public void setAnswerPersonId(String answerPersonId) {
        this.answerPersonId = answerPersonId;
    }

    @Override
    public String toString() {
        return "LrqaPersonAnswer{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", answerPersonId='" + answerPersonId + '\'' +
                '}';
    }
}
