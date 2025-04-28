package com.example.scm.config;

import java.io.IOException;
import java.util.UUID;
import java.util.List;

// import java.util.logging.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
// import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
// import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.scm.entities.Providers;
import com.example.scm.entities.User;
import com.example.scm.helpers.AppConstants;
import com.example.scm.repsitories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    // Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);
    private static final Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("OAuthAuthenticationSuccessHandler");
        
    
        

        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

        logger.info(user.getName());
        user.getAttributes().forEach((key, value) -> {
            logger.info(key + " : " + value);
        });

        logger.info(user.getAuthorities().toString());

        // data database save :
        String email = user.getAttribute("email").toString();
        String name = user.getAttribute("name").toString();
        String picture = user.getAttribute("picture").toString();
        // String provider = user.getAttribute("provider").toString();


        // create user and save in database
        User user1 = new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setProfilePic(picture);
        user1.setPassword("password"); // default password
        user1.setUserId(UUID.randomUUID().toString());
        user1.setProvider(Providers.GOOGLE);
        user1.setEnabled(true);
        user1.setEmailVerified(true);
        user1.setProviderUserId(user.getName());
        user1.setRoleList(List.of(AppConstants.ROLE_USER));
        // user1.setAbout("This Account is created using Google...");

        User user2 = userRepo.findByEmail(email).orElse(null);
        if (user2 == null) {
            userRepo.save(user1);
            logger.info("User created and saved in database" + email);
        } else {
            logger.info("User already exists in database");  
        }


        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile"); {
            
        };
    }

    

}
