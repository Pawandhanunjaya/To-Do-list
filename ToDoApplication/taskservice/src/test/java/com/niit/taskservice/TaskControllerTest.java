package com.niit.taskservice;

import com.niit.taskservice.controller.TaskController;
import com.niit.taskservice.model.Task;
import com.niit.taskservice.service.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
public class TaskControllerTest {
    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Autowired
    private MockMvc mockMvc;

    private Task task;
    private List<Task> tasks;

    @BeforeEach
    public void setup(){
        LocalDate date=LocalDate.parse("2022-05-18");
        task=new Task(10,101,"Gym",false,date,"High","");
        mockMvc= MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @AfterEach
    public void destroy(){
        task=null;
    }

    @Test
    public void givenTaskToAddSuccess() throws Exception{
        when(taskService.addTask(any())).thenReturn(task);

        mockMvc.perform(
                        post("/myapp/v1/task")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(task)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(taskService,times(1)).addTask(any());
    }

    private static String convertToJson(final Object obj){
        String result="";
        try{
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
            result=mapper.writeValueAsString(obj);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            result="JsonProcessingException";
        }
        return result;
    }


    @Test
    public void getAllTask() throws Exception {
        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(
                        get("/myapp/v1/task")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(tasks)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getTaskByUserId() throws Exception {
        when(taskService.getTaskByUserId(task.getUserid())).thenReturn(tasks);

        mockMvc.perform(
                        get("/myapp/v1/task-by-userid/101")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(task)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}
