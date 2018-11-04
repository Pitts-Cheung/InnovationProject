package com.example.pitts.innovationproject.Bean;

import android.widget.ImageView;

import java.io.Serializable;

public class TaskCard implements Serializable {

    private int taskId;
    private String taskTitile;
    private String taskContext;
    private boolean isImageDisplay;
    private String taskImageUrl;
    private String taskTime;
    private String taskGift;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitile() {
        return taskTitile;
    }

    public void setTaskTitile(String taskTitile) {
        this.taskTitile = taskTitile;
    }

    public String getTaskContext() {
        return taskContext;
    }

    public void setTaskContext(String taskContext) {
        this.taskContext = taskContext;
    }

    public boolean isImageDisplay() {
        return isImageDisplay;
    }

    public void setImageDisplay(boolean imageDisplay) {
        isImageDisplay = imageDisplay;
    }

    public String getTaskImageUrl() {
        return taskImageUrl;
    }

    public void setTaskImageUrl(String taskImageUrl) {
        this.taskImageUrl = taskImageUrl;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getTaskGift() {
        return taskGift;
    }

    public void setTaskGift(String taskGift) {
        this.taskGift = taskGift;
    }
}
