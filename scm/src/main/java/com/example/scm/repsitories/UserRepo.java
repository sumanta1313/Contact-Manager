package com.example.scm.repsitories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scm.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,String> {

    // extra methods db relatedroperations
    // custom query methods
    // custom finder methods

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByName(String name);  // 🔁 Fixed this line
}



