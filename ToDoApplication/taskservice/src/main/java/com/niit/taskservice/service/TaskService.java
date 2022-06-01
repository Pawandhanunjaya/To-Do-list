package com.niit.taskservice.service;

import com.niit.taskservice.model.Task;

import java.util.List;

public interface TaskService {

    public List<Task> getAllTasks();
    public Task addTask(Task task) ;
    public List<Task> getTaskByUserId(int userid);
    public Task updateTask(Task task);
    public boolean deleteTask(int taskid) ;
}
