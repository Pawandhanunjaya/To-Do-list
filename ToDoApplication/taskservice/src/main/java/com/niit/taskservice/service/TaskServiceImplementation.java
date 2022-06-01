package com.niit.taskservice.service;

import com.niit.taskservice.model.Task;
import com.niit.taskservice.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImplementation implements TaskService{

    private TaskRepository taskRepository;

    public TaskServiceImplementation(TaskRepository taskRepository) {this.taskRepository=taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task addTask(Task task) {

            return taskRepository.save(task);
    }

    @Override
    public List<Task> getTaskByUserId(int userid) {
        return taskRepository.findByUserid(userid);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public boolean deleteTask(int taskid) {
         taskRepository.deleteById(taskid);
         return  true;
    }
}
