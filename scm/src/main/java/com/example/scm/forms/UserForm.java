package com.example.scm.forms;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Username is required")
    @Size(min = 3,message = "Minimum 3 characters are required")
    private String name;
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6,message = "Minimum 8 characters are required")
    private String password;
    @Size(min = 10, max = 12, message = "Phone number should be of 10 digits")
    private String phoneNumber;
    private String gender;
    private MultipartFile profilePic; 
}

