package com.example.scm.config;




import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.scm.services.impl.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

    // user create and login using java code with in memory service

    // @Bean
    // public UserDetailsService userDetailsService() {
        
    //     UserDetails user1=User
    //     .withDefaultPasswordEncoder()
    //             .username("admin123")
    //             .password("admin123")
    //             .roles("ADMIN","USER")
    //             .build();

    //     UserDetails user2=User
    //         .withUsername("user123")
    //         .password("{noop}user123")
    //         // .roles("ADMIN","USER")
    //         .build();

    //     var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,user2);
    //     return inMemoryUserDetailsManager;
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Autowired
    private OAuthAuthenticationSuccessHandler handler;
    // configuration of authentication provider for spring security

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // user detail service er object:

        daoAuthenticationProvider.setUserDetailsService(userDetailService);

        // passeword encoder er object:

        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
        
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        // configuration

        // urls configuration korechi je kon public hobe r kon gulo private hobe
        httpSecurity
            .authorizeHttpRequests(authorize -> {
                // authorize
                //     .requestMatchers("/home","/register","/services")
                //     .permitAll();

                authorize
                    .requestMatchers("/user/**").authenticated();
                authorize
                    .anyRequest().permitAll();
            });

            // form default login
            // jodi amader kichu change korte hoy tahole amra formLogin() coder er modhye asbo
        httpSecurity.formLogin(formLogin -> {
            formLogin
                .loginPage("/login");
            formLogin
                .loginProcessingUrl("/authenticate");
            formLogin
                .successForwardUrl("/user/dashboard");
            // formLogin
            //     .failureForwardUrl("/login?error=true");
            formLogin
                .usernameParameter("email");
            formLogin
                .passwordParameter("password");

            // formLogin
            //      .failureHandler(new AuthenticationFailureHandler() {

            //         @Override
            //         public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            //                 AuthenticationException exception) throws IOException, ServletException {
            //             // TODO Auto-generated method stub
            //             throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
            //         }       
            // });

            // formLogin
            //     .successHandler(new AuthenticationSuccessHandler() {

            //         @Override
            //         public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            //                 Authentication authentication) throws IOException, ServletException {
            //             // TODO Auto-generated method stub
            //             throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
            //         }
            //     });
            
        }); 

        // csrf configuration
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // logout configuration
        httpSecurity.logout(logoutForm -> {
            logoutForm
                .logoutUrl("/do-logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        });

        // auth configuration

        httpSecurity.oauth2Login(oauth -> {
            oauth
                .loginPage("/login");
            oauth
                .successHandler(handler);
        });



        return httpSecurity.build();

    }

    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
