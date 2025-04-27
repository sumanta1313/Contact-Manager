package com.example.scm.entities;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "User")
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User implements UserDetails {


    @Id
     private String userId = UUID.randomUUID().toString();
    @Column(name = "username", nullable = false)  // ✅ match with DB column
    private String name;
    
    @Column(unique = true,nullable = false)
    private String email;
    @Getter(lombok.AccessLevel.NONE)
    private String password;
    @Column(length=10000)
    private String profilePic;
    private String gender;
    private String phoneNumber;
    @Getter(lombok.AccessLevel.NONE)
    private boolean enabled=true;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    @Enumerated(value = EnumType.STRING)
    // self,google,facebook,twiter,linkedin,github
    private Providers provider=Providers.SELF;
    private String providerUserId;

    // add more fields if needed
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts=new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList=new ArrayList<>(); // admin, user, superadmin, etc.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    
        // list of roles [USER, ADMIN]
        // Collection of SimpleGrantedAuthority [roles{USER,ADMIN}]
        Collection<SimpleGrantedAuthority> roles=roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getUsername'");

        return this.email;
    }


    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }



    // @Override
    // public String getEmail() {
    //     // TODO Auto-generated method stub
    //     return email;
    // }
    // @Override
    // public String getPhoneNumber() {
    //     // TODO Auto-generated method stub
    //     return phoneNumber;
    // }
    // @Override
    // public String getProfilePic() {
    //     // TODO Auto-generated method stub
    //     return profilePic;
    // }



    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return this.enabled;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.password;
    }
}
