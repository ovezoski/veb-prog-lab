package com.finki.lab.service.impl;

import com.finki.lab.model.Enumerations.Role;
import com.finki.lab.model.User;
import com.finki.lab.repository.UserRepository;
import com.finki.lab.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) throws Exception {
        if(username == null || password == null || username.isEmpty() || password.isEmpty()){
            throw new Exception("Invalid Arguments");
        }

        if(!password.equals(repeatPassword)){
            throw new Exception("Passwords don't match");
        }

        if(this.userRepository.findByUsername(username).isPresent()){
            throw new Exception("Username Already exists!");
        }

        User user = new User(username, passwordEncoder.encode(password), name, surname, role);

        return userRepository.save(user);

    }

//    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow();
    }
}