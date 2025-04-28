package com.example.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.scm.entities.User;
import com.example.scm.helpers.Helper;
import com.example.scm.services.UserService;

@ControllerAdvice
public class RootController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    // private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {

        if (authentication == null ) {
            return; // User is not logged in, do not add user information to the model
        }

        System.out.println("Adding logged in user information to model");

        String username = Helper.getEmailOfLoggedInUser(authentication);

        logger.info("User Logged In:{} " , username);
        // database er data fetch : get user from db :

        User user = userService.getUserByEmail(username);

        System.out.println(user);

        
            
            System.out.println(user.getName());
            System.out.println(user.getEmail());
            model.addAttribute("loggedInUser", user); // Add the user object to the model
        


        
        
    }

}
