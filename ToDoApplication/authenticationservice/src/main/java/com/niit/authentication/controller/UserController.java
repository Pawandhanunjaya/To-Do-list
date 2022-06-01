package com.niit.authentication.controller;

import com.niit.authentication.exceptions.UserNotFoundException;
import com.niit.authentication.model.User;
import com.niit.authentication.service.SecurityTokenGenerator;
import com.niit.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/myapp/auth")
public class UserController {

    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService,SecurityTokenGenerator securityTokenGenerator)
    {
        this.userService=userService;
        this.securityTokenGenerator=securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user)
    {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/userservice/getallusers")
    public ResponseEntity<?> getAllUsers()
    {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

  @PostMapping("/login")
  public ResponseEntity<?> loginCheck(@RequestBody User user) throws UserNotFoundException
  {
    Map<String,String> map=null;
    try
    {
      User result=userService.authenticateUser(user.getEmail(), user.getPassword());
      if(result.getEmail().equals(user.getEmail()))
      {
        map=securityTokenGenerator.generateToken(result);
      }
      return new ResponseEntity<>(map,HttpStatus.OK);
    }
    catch(UserNotFoundException ex)
    {
      throw new UserNotFoundException();
    }
    catch (Exception ex)
    {
      return new ResponseEntity<>("Other Exception",HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
