package com.niit.taskservice;

import com.niit.taskservice.controller.UserController;
import com.niit.taskservice.model.User;
import com.niit.taskservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    private User user;
    private List<User> users;

    @BeforeEach
    public void setup(){
        user=new User(101,"User1","1234","sai@123");
        mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
    }

    @AfterEach
    public void destroy(){
        user=null;
    }

    @Test
    public void givenUserToAddSuccess() throws Exception{
        when(userService.addUser(any())).thenReturn(user);

        mockMvc.perform(
                        post("/myapp/v1/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(user)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(userService,times(1)).addUser(any());
    }

    private static String convertToJson(final Object obj){
        String result="";
        try{
            ObjectMapper mapper=new ObjectMapper();
            result=mapper.writeValueAsString(obj);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            result="JsonProcessingException";
        }
        return result;
    }
}
