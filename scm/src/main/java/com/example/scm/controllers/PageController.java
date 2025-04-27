package com.example.scm.controllers;

import com.example.scm.entities.Providers;
import com.example.scm.entities.User;
import com.example.scm.forms.UserForm;
import com.example.scm.helpers.Message;
import com.example.scm.helpers.MessageType;
import com.example.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        System.out.println("Index page loading");
        return "redirect:/home";
    } 
    

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");
        model.addAttribute("name", "substring");
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading");
        return "about";
    }

    @GetMapping("/services")
    public String servicesPage() {
        System.out.println("Services page loading");
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        System.out.println("Contact page loading");
        return "contact";
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("Login page loading");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, HttpSession session) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        System.out.println("Register page loading");
        Message message = (Message) session.getAttribute("message");
    if (message != null) {
        model.addAttribute("message", message);
        session.removeAttribute("message"); // remove after displaying
    }

    return "register";
    }

       // processing register
       @PostMapping("/do-register")
       public String processRegister(@Valid @ModelAttribute("userForm") UserForm userForm,BindingResult rBindingResult, HttpSession session) {
           try {
               System.out.println("Processing registration...");
               System.out.println(userForm);
   
//    validate form data 

if(rBindingResult.hasErrors()) {
    return "register";
}

               // Map form to entity
            //    User user =  User.builder()          
            //            .name(userForm.getName())
            //            .email(userForm.getEmail())
            //            .password(userForm.getPassword())
            //            .gender(userForm.getGender())
            //            .phoneNumber(userForm.getPhoneNumber())
            //            .profilePic("https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75")
            //            .provider(Providers.SELF)
            //            .build();


            User user = new User();
            user.setName(userForm.getName());
            user.setEmail(userForm.getEmail());
            user.setPassword(userForm.getPassword());
            user.setGender(userForm.getGender());
            user.setPhoneNumber(userForm.getPhoneNumber());
            
            user.setProfilePic(
                    "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75");

   
                       user.setUserId(UUID.randomUUID().toString());
               // Save user to DB
               userService.saveUser(user);
               System.out.println("User saved successfully!");
   

            //    Add the message
              
    Message message=Message.builder().content("Registration successful! You can login now").type(MessageType.green).build();

    session.setAttribute("message", message);

   

               return "redirect:/register"; // Don’t redirect if you want to show the message
   
           } catch (Exception e) {
               e.printStackTrace();
               Message message=Message.builder().content("Something gone wrong! This device is going to blast now . ALLAH HU AKBAR !!! ").type(MessageType.red).build();
               session.setAttribute("message", message);
               return "register";
           }
       }
   }
   