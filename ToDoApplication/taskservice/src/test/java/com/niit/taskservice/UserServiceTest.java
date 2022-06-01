package com.niit.taskservice;

import com.niit.taskservice.model.User;
import com.niit.taskservice.repository.UserRepository;
import com.niit.taskservice.service.UserServiceImplementation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImplementation userService;

    private User user;

    @BeforeEach
    public void setup(){
        user=new User(101,"User1","1234","sai@123");
    }

    @AfterEach
    public void destroy(){
        user=null;
    }

    @Test
    public void givenUserToSaveReturnUser() {
        when(userRepository.save(user)).thenReturn(user);
        Assertions.assertEquals(user,userService.addUser(user));
        verify(userRepository,times(1)).save(user);
    }
}
