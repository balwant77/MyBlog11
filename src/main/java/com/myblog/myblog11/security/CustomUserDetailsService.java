package com.myblog.myblog11.security;

import com.myblog.myblog11.entity.Role;
import com.myblog.myblog11.entity.User;
import com.myblog.myblog11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user= userRepository.findByUsernameOrEmail(username,username)//In this either username or emailId is present.We are 2 arguments here as original method has 2 arguments. So to call this method we will pass 2 argument
                .orElseThrow(()->
        new UsernameNotFoundException("User not found with username or email:"+username));//Encoded password will be decoded automatically in background.

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));// Here to user classes are there with similar name so we will give the package name to avoid conflict.
       //we are using this class and setting User object with email password and role: public User(String username, String password, Collection<? extends GrantedAuthority> authorities)
        //loadUserByUsername method: This method is part of the UserDetailsService interface in Spring Security. It is used to load user details based on the provided username. In your implementation, you are using the userRepository to find a user by either username or email.
        // If the user is not found, a UsernameNotFoundException is thrown. If the user is found, the user details are returned as an instance of org.springframework.security.core.userdetails.User. The mapRolesToAuthorities method is used to convert the user's roles to a collection of GrantedAuthority objects.
    }
    private Collection<? extends GrantedAuthority>mapRolesToAuthorities(Set<Role>roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }//This method takes a set of Role objects associated with a user and converts them into a collection of GrantedAuthority objects. It uses Java 8 streams to map each role to a SimpleGrantedAuthority object with the role's name.
    // The result is a collection of authorities representing the user's roles.
}
