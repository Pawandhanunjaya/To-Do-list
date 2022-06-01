package com.niit.authentication.service;

import com.niit.authentication.exceptions.UserNotFoundException;
import com.niit.authentication.model.User;
import com.niit.authentication.proxy.UserProxy;
import com.niit.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserProxy userProxy;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,UserProxy userProxy)
    {
        this.userRepository=userRepository;
        this.userProxy=userProxy;
    }


    @Override
    public User saveUser(User user) {
        User res=userRepository.save(user);

        ResponseEntity response=userProxy.createUser(res);
        System.out.println(response.getBody());
        return res;
    }

  @Override
  public User authenticateUser(String email, String password) throws UserNotFoundException {
    User user=userRepository.findByEmailAndPassword(email,password);
    if(user!=null)
    {
      return user;
    }
    else
    {
      throw new UserNotFoundException();
    }
  }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
