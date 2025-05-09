package com.example.scm.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.scm.entities.Contact;
import com.example.scm.entities.User;
import com.example.scm.forms.ContactForm;
import com.example.scm.helpers.Helper;
import com.example.scm.services.ContactService;
import com.example.scm.services.UserService;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
    // Show the add contact page (GET)
    @GetMapping("/add")
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        contactForm.setName("Nantu da");
        contactForm.setFavorite(false);
        contactForm.setDescription("Number 1 bullshit guy");
        // contactForm.setPicture(null);
        model.addAttribute("contactForm", contactForm);

        return "user/add_contact"; // should resolve to user/add_contact.html
    }

    // Handle form submission (POST)
    @PostMapping("/add")
    public String saveContact(@ModelAttribute ContactForm contactForm,Authentication authentication) {
        // Logic to save the contact form data

String username=Helper.getEmailOfLoggedInUser(authentication);
        
 User user = userService.getUserByEmail(username);


        // Assuming you have a method to get the user by username or email
        // User user = userService.getUserByEmail(username); // or however you get the user  
// Assuming you have a User entity and a method to get the user by username

Contact contact = new Contact();
contact.setName(contactForm.getName());
contact.setEmail(contactForm.getEmail());
contact.setPhoneNumber(contactForm.getPhoneNumber());
contact.setDescription(contactForm.getDescription());
contact.setFavorite(contactForm.isFavorite());
contact.setAddress(contactForm.getAddress());
contact.setUser(user);
contact.setFacebookLink(contactForm.getFacebookLink());
contact.setInstaLink(contactForm.getInstaLink());
contact.setLinkedInLink(contactForm.getLinkedInLink());
contact.setWebsiteLink(contactForm.getWebsiteLink());


        contactService.save(contact);
        System.out.println(contactForm);
        // You can call a service here to save to the database

        // Redirect to the same add page (or change this to wherever you need)
        return "redirect:/user/contact/add";
    }
}
