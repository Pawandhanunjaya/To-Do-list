package com.niit.taskservice.repository;

import com.niit.taskservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User,Integer> {


}
