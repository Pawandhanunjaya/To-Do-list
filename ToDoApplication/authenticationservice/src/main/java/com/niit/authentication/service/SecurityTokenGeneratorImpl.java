package com.niit.authentication.service;

import com.niit.authentication.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator{


  @Override
  public Map<String, String> generateToken(User user) {
    Date d=new Date();
    d.setMinutes(d.getMinutes()+15);

    String jwttoken=  Jwts.builder()
      .setSubject(user.getUsername())
      .setIssuedAt(new Date())
      .setExpiration(d)
      .signWith(SignatureAlgorithm.HS256,"mykey").compact();
    Map<String ,String> map=new HashMap<>();
    map.put("token",jwttoken);
    map.put("UserName",user.getUsername());
    map.put("userid", String.valueOf(user.getUserid()));
    map.put("message","Logged in Successfully.");

    return map;
  }
}
