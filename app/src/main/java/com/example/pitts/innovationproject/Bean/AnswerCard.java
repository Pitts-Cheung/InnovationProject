package com.example.pitts.innovationproject.Bean;

public class AnswerCard {

    private int answerId;
    private String answerUserIconUrl;
    private String answerUserName;
    private String answerContext;
    private boolean isImageDisplay;
    private String answerImageUrl;
    private String answerTime;
    private int answerAgreement;
    private int answerComment;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswerUserIconUrl() {
        return answerUserIconUrl;
    }

    public void setAnswerUserIconUrl(String answerUserIconUrl) {
        this.answerUserIconUrl = answerUserIconUrl;
    }

    public String getAnswerUserName() {
        return answerUserName;
    }

    public void setAnswerUserName(String answerUserName) {
        this.answerUserName = answerUserName;
    }

    public String getAnswerContext() {
        return answerContext;
    }

    public void setAnswerContext(String answerContext) {
        this.answerContext = answerContext;
    }

    public boolean isImageDisplay() {
        return isImageDisplay;
    }

    public void setImageDisplay(boolean imageDisplay) {
        isImageDisplay = imageDisplay;
    }

    public String getAnswerImageUrl() {
        return answerImageUrl;
    }

    public void setAnswerImageUrl(String answerImageUrl) {
        this.answerImageUrl = answerImageUrl;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    public int getAnswerAgreement() {
        return answerAgreement;
    }

    public void setAnswerAgreement(int answerAgreement) {
        this.answerAgreement = answerAgreement;
    }

    public int getAnswerComment() {
        return answerComment;
    }

    public void setAnswerComment(int answerComment) {
        this.answerComment = answerComment;
    }
}
