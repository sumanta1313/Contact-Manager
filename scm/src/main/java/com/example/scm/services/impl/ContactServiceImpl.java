package com.example.scm.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scm.entities.Contact;
import com.example.scm.helpers.ResourceNotFoundException;
import com.example.scm.repsitories.ContactRepo;
import com.example.scm.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private  ContactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {
      String ContactId = UUID.randomUUID().toString();
      contact.setId(ContactId);
      return contactRepo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Contact> getAll() {
       return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
       return contactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + id));
        // return contactRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
       var contact = contactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + id));
      contactRepo.delete(contact);
        
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public List<Contact> getByUserId(String userId) {
       return contactRepo.findByUserId(userId);
    }

}
