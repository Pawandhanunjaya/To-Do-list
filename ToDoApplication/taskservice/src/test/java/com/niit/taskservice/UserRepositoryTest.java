package com.niit.taskservice;

import com.niit.taskservice.model.User;
import com.niit.taskservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setup(){
        user=new User(101,"User1","1234","sai@123");
    }

    @AfterEach
    public void destroy(){
        user=null;
        userRepository.deleteAll();
    }

    @Test
    public void givenUserToSaveReturnUser(){
        userRepository.insert(user);
        User result=userRepository.findById(user.getUserid()).get();
        assertNotNull(result);
        assertEquals(result.getUserid(),user.getUserid());
        assertEquals(result.getUsername(),user.getUsername());
    }

    @Test
    public void givenProductToSaveReturnError(){
        userRepository.insert(user);
        assertThrows(DuplicateKeyException.class,()->userRepository.insert(user));
    }
}
