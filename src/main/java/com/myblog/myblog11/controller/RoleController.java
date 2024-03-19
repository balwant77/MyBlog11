package com.myblog.myblog11.controller;

import com.myblog.myblog11.entity.Role;
import com.myblog.myblog11.payload.RoleDto;
import com.myblog.myblog11.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;
    @PostMapping
    public ResponseEntity<?> saveRole (@RequestBody RoleDto roleDto){
        Role role = new Role();
        role.setName(roleDto.getName());

        roleRepository.save(role);

        return new ResponseEntity<>("role is set successfully", HttpStatus.CREATED);

    }
}
