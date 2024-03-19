package com.myblog.myblog11.controller;

import com.myblog.myblog11.entity.Role;
import com.myblog.myblog11.entity.User;
import com.myblog.myblog11.payload.LoginDto;
import com.myblog.myblog11.payload.SignUpDto;
import com.myblog.myblog11.repository.RoleRepository;
import com.myblog.myblog11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;//This has username and password is coming from the database. When we will start the spring boot,
    // springboot will first raed the config file and in SecurityConfig file we have userDetailsService object which have user data fetched from the database and we are passing it to the AuthenticationManager object.
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
   private  RoleRepository roleRepository;
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!!", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken",HttpStatus.BAD_REQUEST);
        }

        User user=new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));//passwordEncoder gives BCryptPasswordEncoder object which have encode method.
        Role roles = roleRepository.findByName(signUpDto.getRoleType()).get();// The get() method is used to extract the value from the Optional.
        Set<Role> convertRoleToSet=new HashSet<>();//this line creates a HashSet called convertRoleToSet to store roles.
        convertRoleToSet.add(roles);//Here, the retrieved role (in this case, "ROLE_ADMIN") is added to the convertRoleToSet HashSet.
        user.setRoles(convertRoleToSet);
        // Assuming there is a user object with a method setRoles that accepts a set of roles, this line sets the roles for the user.
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully",HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<String>authenticateUser(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword());//UsernamePasswordAuthenticationToken class will supply the username to the
//loadUserByUsername method present in CustomUserDetailsService which is implementing the UserDetailsService Interface.
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //After Authentication, we will set the session variable.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return  new ResponseEntity<>("User signed-in successfully!.",HttpStatus.OK);

    }
}
//First we will call the sign in method and pass the username and password through postman.
//After that we will pass this username and password to loadByUserName method which is provided by spring security which is present inside UserDetailsService interface.
//We will override and complete that method by passing username or email to it.object of UsernamePasswordAuthenticationToken class will supply the username to the
//loadUserByUsername method present in CustomUserDetailsService which is implementing the UserDetailsService Interface
//Now we will use repository layer to search this record in database by passing the data from loadByUsername method.
//After searching, it will return the user object or throw an exception.
//If user object is found we will then put that user object into spring security User object by setting up username/email,password and role.
//After this we will create a bean CustomUserDetailsService in security config file. Moment we create a bean of this, loadByUsername object will be present in this class.
//we have to give this bean to configure method and this takes user details from service class and tells spring boot this username and password from database.
//Now we will compare the details in auth controller. By creating the bean of the authentication manager in auth controller all the username and password through config file we give it to the spring security will pass to authentication manger class.
//Details entered by user is present in usernamePasswordAuthenticationToken
//authentication manager will perform the comparison.
//SecurityContextHolder will set the session variable.
