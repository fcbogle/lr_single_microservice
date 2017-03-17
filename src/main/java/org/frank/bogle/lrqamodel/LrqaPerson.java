package org.frank.bogle.lrqamodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frankbogle on 21/06/2016.
 */
@Document
public class LrqaPerson {

    @Id
    private String id;

    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String country;
    private String authority;

    @DBRef
    private List<LrqaBosQuestion> submittedQuestions;

    public LrqaPerson(){
        submittedQuestions = new ArrayList<>();
    }

    public LrqaPerson(String email, String password){
        this();
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<LrqaBosQuestion> getSubmittedQuestions() {
        return submittedQuestions;
    }

    public void setSubmittedQuestions(List<LrqaBosQuestion> submittedQuestions) {
        this.submittedQuestions = submittedQuestions;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void addLrqaBosQuestion(LrqaBosQuestion lrqaBosQuestion){
        this.submittedQuestions.add(lrqaBosQuestion);
    }

    @Override
    public String toString() {
        return "LrqaPerson{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", country='" + country + '\'' +
                ", authority='" + authority + '\'' +
                ", submittedQuestions=" + submittedQuestions +
                '}';
    }
}
