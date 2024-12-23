package com.finki.lab.service;


import com.finki.lab.model.Enumerations.Role;
import com.finki.lab.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService
//        extends UserDetailsService
{
    User register(String username, String password, String repeatPassword, String name, String surname, Role role) throws Exception;
    User loadUserByUsername(String username);
}
