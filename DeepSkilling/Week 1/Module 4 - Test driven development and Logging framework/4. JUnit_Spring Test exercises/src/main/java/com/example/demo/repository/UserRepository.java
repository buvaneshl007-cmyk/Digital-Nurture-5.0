package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Exercise 7: custom query method. Spring Data JPA derives the query
    // automatically from the method name: "find a User by its name field".
    List<User> findByName(String name);
}
