package com.niit.authentication.service;

import com.niit.authentication.exceptions.UserNotFoundException;
import com.niit.authentication.model.User;

import java.util.List;

public interface UserService {
  public abstract User saveUser(User user);

  public abstract User authenticateUser(String email, String password) throws UserNotFoundException;

  public abstract List<User> getAllUsers();
}