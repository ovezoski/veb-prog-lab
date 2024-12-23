package com.finki.lab.bootstrap;

import com.finki.lab.model.Enumerations.Role;
import com.finki.lab.model.User;
import com.finki.lab.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<User> users;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataHolder(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        users = new ArrayList<>();
    }

    @PostConstruct
    void init(){

        users.add(new User("elena.atanasoska", passwordEncoder.encode("ea"), "Elena", "Atanasoska", Role.ROLE_USER));
        users.add(new User("darko.sasanski", passwordEncoder.encode("ds"), "Darko", "Sasanski", Role.ROLE_USER));
        users.add(new User("ana.todorovska", passwordEncoder.encode("at"), "Ana", "Todorovska", Role.ROLE_USER));
        users.add(new User("admin", passwordEncoder.encode("admin"), "Admin", "Adminoski", Role.ROLE_ADMIN));
        this.userRepository.saveAll(users);


    }

}
