package com.example.scm.entities;

 import jakarta.annotation.Generated;
 import jakarta.persistence.Entity;
 import jakarta.persistence.GeneratedValue;
 import jakarta.persistence.GenerationType;
 import jakarta.persistence.Id;
 import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
 import lombok.Builder;
 import lombok.Getter;
 import lombok.NoArgsConstructor;
 import lombok.Setter;
 
 @Entity
@Table(name = "social_links")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
 public class SocialLink {
 
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String link;
     private String title;
 
     @ManyToOne
     private Contact contact;
 }