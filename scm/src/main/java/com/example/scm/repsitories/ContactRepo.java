package com.example.scm.repsitories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.scm.entities.Contact;
import com.example.scm.entities.User;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String> {
// find the contact by user id
<<<<<<< HEAD
    Page<Contact> findByUser(User user,Pageable pageable);
=======
    Page<Contact> findByUser(User user, Pageable pageable);
>>>>>>> 55e0767e70838623f7b5cb20fc96c2b7009dc7c5

   @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
   List<Contact> findByUserId(@Param("userId")String userId);
}
