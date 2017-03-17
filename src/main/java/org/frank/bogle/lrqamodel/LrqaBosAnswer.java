package org.frank.bogle.lrqamodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by frankbogle on 21/06/2016.
 */
@Document
public class LrqaBosAnswer {

    @Id
    private String id;

    private String questionText;

    private String answerText;

    @DBRef
    private LrqaPersonAnswer answerCreator;

    public LrqaBosAnswer(){}

    public LrqaBosAnswer(String answerText){
        this();
        this.answerText = answerText;
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

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public LrqaPersonAnswer getAnswerCreator() {
        return answerCreator;
    }

    public void setAnswerCreator(LrqaPersonAnswer answerCreator) {
        this.answerCreator = answerCreator;
    }

    @Override
    public String toString() {
        return "LrqaBosAnswer{" +
                "id='" + id + '\'' +
                ", questionText='" + questionText + '\'' +
                ", answerText='" + answerText + '\'' +
                ", answerCreator=" + answerCreator +
                '}';
    }
}
