package com.barber.apiBarber.Services;

import com.barber.apiBarber.Dto.UserDto;
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

        public User registerUser(UserDto userDTO) {
            User user = new User();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Criptografando senha
            user.setRole(userDTO.getRole());
            user.setAtendeDomicilio(userDTO.getAtendeDomicilio());
            user.setServicos(userDTO.getServicos());
            user.setContato(userDTO.getContato());

            return userRepository.save(user);
        }
    }

