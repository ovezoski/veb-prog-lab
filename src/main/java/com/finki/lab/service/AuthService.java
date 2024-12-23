package com.finki.lab.service;

import com.finki.lab.model.User;

public interface AuthService {
    User login(String username, String password) throws Exception;
}
