package com.example.pitts.innovationproject.Bean;

public class QuestionCard {

    private int questionId;
    private String questionTitle;
    private String questionContext;
    private boolean isImageDisplay;
    private String questionImageUrl;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionContext() {
        return questionContext;
    }

    public void setQuestionContext(String questionContext) {
        this.questionContext = questionContext;
    }

    public boolean isImageDisplay() {
        return isImageDisplay;
    }

    public void setImageDisplay(boolean imageDisplay) {
        isImageDisplay = imageDisplay;
    }

    public String getQuestionImageUrl() {
        return questionImageUrl;
    }

    public void setQuestionImageUrl(String questionImageUrl) {
        this.questionImageUrl = questionImageUrl;
    }
}
