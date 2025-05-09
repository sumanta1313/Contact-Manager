package com.example.scm.services;

import java.util.List;

import com.example.scm.entities.Contact;

public interface ContactService {
// save contact
    Contact save(Contact contact);

// update contact
    Contact update(Contact contact);

    // get contact
    List<Contact> getAll();

    // get contact by id
    Contact getById(String id);

    // delete contact
    void deleteById(String id);

    // search contact by name
    List<Contact> search(String name,String email, String phoneNumber);

    // get all contacts by user id
    List<Contact> getByUserId(String userId);
}
