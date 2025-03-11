package com.barber.apiBarber.Controller;

import com.barber.apiBarber.Dto.UserDto;
import com.barber.apiBarber.Model.User;
import com.barber.apiBarber.Repository.UserRepository;
import com.barber.apiBarber.Security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    private  final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        Optional<User> FoundUser = userRepository.findByUsername(user.getUsername());

        if (FoundUser.isPresent() && FoundUser.get().getPassword().equals(user.getPassword())){
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok().body("{\"acessToken" + token);
        }

        return ResponseEntity.status(401).body("Credenciais inválidas.");
    }
}