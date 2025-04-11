package com.example.scm.services.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scm.entities.Providers;
import com.example.scm.entities.User;
import com.example.scm.helpers.ResourceNotFoundException;
import com.example.scm.repsitories.UserRepo;
import com.example.scm.services.UserService;

@Service
 public class UserServiceImpl implements UserService {
 
     @Autowired
     private UserRepo userRepo;
 
     private Logger logger = LoggerFactory.getLogger(this.getClass());
 
     @Override
     public User saveUser(User user) {
         // Generate user ID
         String userId   = UUID.randomUUID().toString();
         user.setUserId(userId);
     
         // Explicitly set default provider to SELF to avoid null
         user.setProvider(Providers.SELF); // set explicitly to avoid null
     
         // Logging provider
         if (user.getProvider() != null) {
             logger.info(user.getProvider().toString());
         } else {
             logger.warn("Provider is null for user: " + user.getEmail());
         }
     
         // Save user
         return userRepo.save(user);
     }
     
 
     @Override
     public Optional<User> getUserById(String id) {
         return userRepo.findById(id);
     }
 
     @Override
     public Optional<User> updateUser(User user) {
 
         User user2 = userRepo.findById(user.getUserId())
                 .orElseThrow(() -> new ResourceNotFoundException("User not found"));
         // update karenge user2 from user
         user2.setName(user.getName());
         user2.setEmail(user.getEmail());
         user2.setPassword(user.getPassword());
         user2.setGender(user.getGender());
         user2.setPhoneNumber(user.getPhoneNumber());
         user2.setProfilePic(user.getProfilePic());
         user2.setEnabled(user.isEnabled());
         user2.setEmailVerified(user.isEmailVerified());
         user2.setPhoneVerified(user.isPhoneVerified());
         user2.setProvider(user.getProvider());
         user2.setProviderUserId(user.getProviderUserId());
         // save the user in database
         User save = userRepo.save(user2);
         return Optional.ofNullable(save);
 
     }
 
     @Override
     public void deleteUser(String id) {
         User user2 = userRepo.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("User not found"));
         userRepo.delete(user2);
 
     }
 
     @Override
     public boolean isUserExist(String userId) {
         User user2 = userRepo.findById(userId).orElse(null);
         return user2 != null ? true : false;
     }
 
     @Override
     public boolean isUserExistByUserName(String userName) {
         return userRepo.findByName(userName).isPresent();
     }
     

     @Override
     public boolean isUserExistByEmail(String email) {
         User user = userRepo.findByEmail(email).orElse(null);
         return user != null ? true : false;
     }

 
     @Override
     public List<User> getAllUsers() {
         return userRepo.findAll();
     }
 
 }
