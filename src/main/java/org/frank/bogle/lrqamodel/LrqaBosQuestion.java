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
public class LrqaBosQuestion {

    @Id
    private String id;

    private String questionText;

    private String questionTitle;

    @DBRef
    private LrqaPersonQuestion questionCreator;

    @DBRef
    private List<LrqaBosAnswer> answerResponders;

    public LrqaBosQuestion(){
        answerResponders = new ArrayList<>();
    }

    public LrqaBosQuestion(String questionText){
        this();
        this.questionText = questionText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public LrqaPersonQuestion getQuestionCreator() {
        return questionCreator;
    }

    public void setQuestionCreator(LrqaPersonQuestion questionCreator) {
        this.questionCreator = questionCreator;
    }

    public List<LrqaBosAnswer> getAnswerResponders() {
        return answerResponders;
    }

    public void setAnswerResponders(List<LrqaBosAnswer> answerResponders) {
        this.answerResponders = answerResponders;
    }

    @Override
    public String toString() {
        return "LrqaBosQuestion{" +
                "id='" + id + '\'' +
                ", questionText='" + questionText + '\'' +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionCreator=" + questionCreator +
                ", answerResponders=" + answerResponders +
                '}';
    }
}
