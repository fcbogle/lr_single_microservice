package org.frank.bogle.lrqamodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by frankbogle on 25/06/2016.
 */
@Document
public class LrqaPersonQuestion {

    @Id
    private String id;

    private String firstname;
    private String lastname;
    private String country;
    private String questionPersonId;
    private String email;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getQuestionPersonId() {
        return questionPersonId;
    }

    public void setQuestionPersonId(String questionPersonId) {
        this.questionPersonId = questionPersonId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "LrqaPersonQuestion{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", country='" + country + '\'' +
                ", questionPersonId='" + questionPersonId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
