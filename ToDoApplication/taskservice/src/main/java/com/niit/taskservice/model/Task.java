package com.niit.taskservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Task {

    @Id
    private  int taskid;
    private int userid;
    private  String title;
    private  boolean status;
    private LocalDate date;
    private String priority;
    private String image;

    public Task() {
    }

    public Task(int taskid, int userid, String title, boolean status, LocalDate date, String priority, String image) {
        this.taskid = taskid;
        this.userid = userid;
        this.title = title;
        this.status = status;
        this.date = date;
        this.priority = priority;
        this.image = image;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskid=" + taskid +
                ", userid=" + userid +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", date=" + date +
                ", priority='" + priority + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
