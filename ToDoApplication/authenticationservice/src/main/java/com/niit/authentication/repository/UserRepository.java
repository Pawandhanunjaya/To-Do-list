package com.niit.authentication.repository;

import com.niit.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {



  public abstract User findByEmailAndPassword(String userid,String password);


}
