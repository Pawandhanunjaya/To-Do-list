package com.niit.taskservice;

import com.niit.taskservice.model.Task;
import com.niit.taskservice.repository.TaskRepository;
import com.niit.taskservice.service.TaskServiceImplementation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImplementation taskService;

    private Task task;

    @BeforeEach
    public void setup(){
        LocalDate date=LocalDate.parse("2022-05-18");
        task=new Task(10,101,"Gym",false,date,"High","");
    }

    @AfterEach
    public void destroy(){
        task=null;
    }

    @Test
    public void givenTaskToSaveReturnTask() {
        when(taskRepository.save(task)).thenReturn(task);
        assertEquals(task,taskService.addTask(task));
        verify(taskRepository,times(1)).save(task);
    }

    @Test
    public void givenTaskToDelete(){
        boolean result=taskService.deleteTask(task.getTaskid());
        assertEquals(true,result);
        verify(taskRepository,times(1)).deleteById(task.getTaskid());
    }

    @Test
    public void getAllTasks(){
        when(taskRepository.findAll()).thenReturn(Collections.singletonList(task));
        List<Task> task=taskService.getAllTasks();
        assertEquals(1,task.size());
        verify(taskRepository,times(1)).findAll();
    }

    @Test
    public void getAllTasksByUser(){
        when(taskRepository.findByUserid(task.getUserid())).thenReturn(Collections.singletonList(task));
        List<Task> tasks=taskService.getTaskByUserId(task.getUserid());
        assertEquals(1,tasks.size());
        verify(taskRepository,times(1)).findByUserid(task.getUserid());
    }

    @Test
    public void givenTaskReturnUpdatedTask() {
        Task task1=new Task(task.getTaskid(),task.getUserid(),task.getTitle(),task.isStatus(),task.getDate(),task.getPriority(),task.getImage());
        when(taskRepository.save(task)).thenReturn(task);
        taskRepository.save(task);
        task1.setTitle("Movie");
        when(taskRepository.save(task1)).thenReturn(task1);
        taskService.updateTask(task1);
        assertNotEquals(task.getTitle(),task1.getTitle());
    }
}
