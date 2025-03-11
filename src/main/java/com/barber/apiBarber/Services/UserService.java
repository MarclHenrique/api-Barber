package com.barber.apiBarber.Services;

import com.barber.apiBarber.Model.User;
import com.barber.apiBarber.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Criptografando senha
        return userRepository.save(user);
    }
}

