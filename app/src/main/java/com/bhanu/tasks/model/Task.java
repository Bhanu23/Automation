package com.bhanu.tasks.model;

import java.io.Serializable;

public class Task implements Serializable {

    int id, taskID;
    String name;
    long time;

    String task_date, task_time;

    public Task() {

    }

    public Task(int id, String name, long time, int eventID, String task_date, String task_time) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.taskID = eventID;
        this.task_date = task_date;
        this.task_time = task_time;
    }

    public String getTask_date() {
        return task_date;
    }

    public void setTask_date(String task_date) {
        this.task_date = task_date;
    }

    public String getTask_time() {
        return task_time;
    }

    public void setTask_time(String task_time) {
        this.task_time = task_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getTaskId() {
        return taskID;
    }

    public void setTaskId(int eventID) {
        this.taskID = eventID;
    }

    @Override
    public String toString() {
        return name;
    }
}
