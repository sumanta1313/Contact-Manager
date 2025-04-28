package com.example.scm.helpers;

import java.net.Authenticator;
// import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {


        // AuthenticationPrincipal principal = (AuthenticationPrincipal)authentication.getPrincipal();

        // jodi ai password diye login korechi to : email ki kore ber korbo

        if (authentication instanceof OAuth2AuthenticationToken) {
            
            // Sign with Google, Github, etc. er khetre email ta ki vabe ber korbo

            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            var oauth2User = (OAuth2User)authentication.getPrincipal();

            String username = "";

            if (clientId.equalsIgnoreCase("google")) {

                // Sign with Google, Github, etc. er khetre email ta ki vabe ber korbo
                System.out.println("Getting email from Google client");

                
                username = oauth2User.getAttribute("email").toString();
                
            } else if (clientId.equalsIgnoreCase("github")) {

                // Sign with Google, Github, etc. er khetre email ta ki vabe ber korbo
                System.out.println("Getting email from GitHub client");

                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString() : oauth2User.getAttribute("login").toString() + "@gmail.com";
                
            } 

            return username;

        } else{
            System.out.println("Getting Data Form Local Database");
            return authentication.getName(); // Return the email of the logged-in user
        }
        
    }

}
