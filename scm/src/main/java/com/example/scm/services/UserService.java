package com.example.scm.services;


import java.util.List;
 import java.util.Optional;
 
 import com.example.scm.entities.User;

 
 
 public interface UserService {
 
     User saveUser(User user);
 
     Optional<User> getUserById(String id);
 
     Optional<User> updateUser(User user);
 
     void deleteUser(String id);
 
     boolean isUserExist(String userId);
 
     boolean isUserExistByUserName(String username);
    
     boolean isUserExistByEmail(String email);
 
     List<User> getAllUsers();

     User getUserByEmail(String email);
 
     // add more methods here related user service[logic]
 
 }
 