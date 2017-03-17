package org.frank.bogle.lrqamodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by frankbogle on 22/07/2016.
 */
@Document
public class LrqaAppFeedback {

    @Id
    private String id;

    private String email;
    private String feedback;

    public LrqaAppFeedback(){}

    public LrqaAppFeedback(String email){
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "LrqaAppFeedback{" +
                "email='" + email + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
