package com.barber.apiBarber.controller;

import com.barber.apiBarber.services.TokenService;
import com.barber.apiBarber.dtos.AuthDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDTO authDTO){
        var auth = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.senha());
        authenticationManager.authenticate(auth);
        String token = tokenService.obterToken(authDTO);
        return ResponseEntity.ok(token);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_BARBEIRO')")
    public String test(){
        return "ok";
    }

}