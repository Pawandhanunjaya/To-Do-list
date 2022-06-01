package com.niit.authentication.service;

import com.niit.authentication.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    public Map<String,String > generateToken(User user);
}
