package com.example.scm.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.scm.entities.Contact;
import com.example.scm.entities.User;
import com.example.scm.forms.ContactForm;
import com.example.scm.helpers.AppConstants;
import com.example.scm.helpers.Helper;
import com.example.scm.helpers.Message;
import com.example.scm.helpers.MessageType;
import com.example.scm.services.ContactService;
import com.example.scm.services.ImageService;
import com.example.scm.services.UserService;
// import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/user/contact")
public class ContactController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    // Show the add contact page (GET)
    @GetMapping("/add")
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        contactForm.setName("Nantu da");
        contactForm.setFavorite(false);
        contactForm.setDescription("Number 1 bullshit guy");
        model.addAttribute("contactForm", contactForm);

        return "user/add_contact"; // should resolve to user/add_contact.html
    }

    // Handle form submission (POST)
    @PostMapping("/add")
  public String saveContact(
        @Valid @ModelAttribute("contactForm") ContactForm contactForm,
        BindingResult bindingResult,
        Authentication authentication,
        HttpSession session,  // Added this
        Model model) {

    if (bindingResult.hasErrors()) {
        // If there are validation errors, return the form page again

        bindingResult.getAllErrors().forEach(error -> logger.info("Validation error: {}", error.toString()));

        session.setAttribute("message",
            Message.builder()
                   .content("Contact can't be added")
                   .type(MessageType.red)
                   .build());
        return "user/add_contact";
    }

        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        // image processing
        // System.out.println(contactForm.getProfileImage());
        // logger.info("file information: {}", contactForm.getProfileImage().getOriginalFilename());

 // image processing
String filename = UUID.randomUUID().toString();
String fileURL = imageService.uploadimage(contactForm.getContactImage(), filename);



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
        contact.setPicture(fileURL);

contact.setCloudinaryImagePublicId(filename);
        
        contactService.save(contact);

        System.out.println(contactForm);
       session.setAttribute("message", 
        Message.builder()
               .content("Contact added successfully!")
               .type(MessageType.green)
               .build());

        // Redirect to the same add page (or change this to wherever you need)
        return "redirect:/user/contact/add";
    }

// View Contacts

@RequestMapping
    public String viewContacts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction, Model model,
            Authentication authentication) {

        // load all the user contacts
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);

        List<Contact> contacts = contactService.getByUser(user);

        model.addAttribute("contacts", contacts);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

        return "user/contacts";
    }
}

