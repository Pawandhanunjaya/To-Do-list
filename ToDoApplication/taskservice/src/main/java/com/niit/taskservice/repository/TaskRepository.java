package com.niit.taskservice.repository;

import com.niit.taskservice.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task,Integer> {
    public List<Task> findByUserid(int userid);
}
