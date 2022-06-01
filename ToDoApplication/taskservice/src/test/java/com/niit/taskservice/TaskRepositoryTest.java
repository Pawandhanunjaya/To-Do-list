package com.niit.taskservice;

import com.niit.taskservice.model.Task;
import com.niit.taskservice.repository.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;

    private Task task;

    @BeforeEach
    public void setup(){
        LocalDate date=LocalDate.parse("2022-05-18");

        task=new Task(10,101,"Gym",false, date,"High","");
    }

    @AfterEach
    public void destroy(){
        task=null;
        taskRepository.deleteAll();
    }

    @Test
    public void givenTaskToSaveReturnTask(){
        taskRepository.insert(task);
        Task result=taskRepository.findById(task.getTaskid()).get();
        assertNotNull(result);
        assertEquals(result.getTaskid(),task.getTaskid());
        assertEquals(result.getTitle(),task.getTitle());
    }

    @Test
    public void givenTaskToDelete(){
        taskRepository.insert(task);
        taskRepository.deleteById(task.getTaskid());
        assertEquals(Optional.empty(),taskRepository.findById(task.getTaskid()));
    }

    @Test
    public void givenAllProductDetails(){
        taskRepository.insert(task);
        List<Task> task=taskRepository.findAll();
        assertEquals(1,task.size());
        assertNotEquals(0,task.size());
        assertNotNull(task.size());
    }

    @Test
    public void givenGetTaskByUser(){
        taskRepository.insert(task);
        List<Task> products=taskRepository.findByUserid(task.getUserid());
        assertEquals(1,products.size());
    }

    @Test
    public void givenTaskReturnUpdatedTask(){
        taskRepository.save(task);
        Task task1=taskRepository.findById(task.getTaskid()).get();
        task1.setTitle("Movie");
        taskRepository.save(task1);
        assertNotEquals(task.getTitle(),task1.getTitle());
    }
}
