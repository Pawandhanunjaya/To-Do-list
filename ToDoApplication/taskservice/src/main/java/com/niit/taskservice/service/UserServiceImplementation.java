package com.niit.taskservice.service;

import com.niit.taskservice.model.User;
import com.niit.taskservice.rabbitmq.EmailDTO;
import com.niit.taskservice.rabbitmq.Producer;
import com.niit.taskservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{

    private UserRepository userRepository;

    @Autowired
    private Producer producer;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {this.userRepository=userRepository;
    }

    @Override
    public User addUser(User user) {
        EmailDTO emailDTO=new EmailDTO();
        emailDTO.setEmail(user.getEmail());
        producer.sendMessageToMq(emailDTO);
        return userRepository.save(user);
    }
}
