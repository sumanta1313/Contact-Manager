package com.example.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    // User Dashboard page

    @RequestMapping(value="/dashboard")
    public String userDashboard() {
        return "user/dashboard"; // Return the view name for the user dashboard page
    }

    // User Profile page

    
    @RequestMapping(value="/profile")
    public String userProfile() {
        return "user/profile"; // Return the view name for the user profile page
    }


    // User Add contacts page






    // User View contacts page





    // User Edit contacts page




    // User Delete contacts page




    

}
