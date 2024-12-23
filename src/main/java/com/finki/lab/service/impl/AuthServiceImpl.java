package com.finki.lab.service.impl;

import com.finki.lab.model.User;
import com.finki.lab.repository.UserRepository;
import com.finki.lab.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository){

        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) throws Exception {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new Exception("Invalid credentials!");
        }

        return userRepository.findByUsernameAndPassword(username, password).orElseThrow();
    }
}
