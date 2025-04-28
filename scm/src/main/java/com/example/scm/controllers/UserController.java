package com.example.scm.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.scm.entities.User;
import com.example.scm.helpers.Helper;
import com.example.scm.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    

    // User Dashboard page

    @RequestMapping(value="/dashboard")
    public String userDashboard() {
        return "user/dashboard"; // Return the view name for the user dashboard page
    }

    // User Profile page

    
    @RequestMapping(value="/profile")
    public String userProfile(Model model, Authentication authentication) {

        return "user/profile"; // Return the view name for the user profile page
    }


    // User Add contacts page






    // User View contacts page





    // User Edit contacts page




    // User Delete contacts page




    

}
