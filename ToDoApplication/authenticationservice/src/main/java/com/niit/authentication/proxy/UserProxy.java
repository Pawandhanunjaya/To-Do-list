package com.niit.authentication.proxy;

import com.niit.authentication.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="userproduct",url="localhost:9696")
public interface UserProxy {

    @PostMapping("/myapp/v1/user")
    public ResponseEntity<?> createUser(@RequestBody User user);
}
