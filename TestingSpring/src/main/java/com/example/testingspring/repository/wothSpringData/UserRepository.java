package com.example.testingspring.repository.wothSpringData;


import com.example.testingspring.DataClass.ObjectForTaco.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


public interface UserRepository extends CrudRepository<User,String> {


    User findByUsername(String username);
}
