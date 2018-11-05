package com.example.pitts.innovationproject.Bean;

import java.util.ArrayList;

public class Task {

    private String taskTitle;

    private String taskContent;

    private int UserId;

    private String taskTime;

    private int taskGift;

    private ArrayList<Integer> taskDDLDate;

    private ArrayList<Integer> taskDDLTime;

    private String taskType;

    public Task(String taskTitle, String taskContent, int userId, String taskTime, int taskGift, ArrayList<Integer> taskDDLDate, ArrayList<Integer> taskDDLTime, String taskType) {
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        UserId = userId;
        this.taskTime = taskTime;
        this.taskGift = taskGift;
        this.taskDDLDate = taskDDLDate;
        this.taskDDLTime = taskDDLTime;
        this.taskType = taskType;
    }
}
