package com.myblog.myblog11.repository;

import com.myblog.myblog11.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role>findByName(String name);//This method returns an Optional object wrapping a Role instance.
    // An Optional is a container object that may or may not contain a non-null value. It is often used to represent an optional result in cases where the value might be absent.

}
