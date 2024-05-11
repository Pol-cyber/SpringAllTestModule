package com.example.tsauthorizeserver.repository;


import com.example.tsauthorizeserver.myObject.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {

    User findByUsername(String username);
}
