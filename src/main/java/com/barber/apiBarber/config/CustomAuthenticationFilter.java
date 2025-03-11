package com.barber.apiBarber.config;

import com.barber.apiBarber.model.User;
import com.barber.apiBarber.Repository.UserRepository;
import com.barber.apiBarber.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private UserRepository usuarioRepository;
    private TokenService tokenService;

    @Autowired
    public CustomAuthenticationFilter(UserRepository usuarioRepository, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extrairTokenDoHeader(request);

        if(token != null){
            String login = tokenService.obterLogin(token);
            User user = usuarioRepository.findByEmail(login).orElseThrow(() -> new UsernameNotFoundException("Usuário inexistente."));
            var authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private String extrairTokenDoHeader(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");

        if(authHeader == null){
            return null;
        }

        if(!authHeader.split(" ")[0].equals("Bearer")){
            return null;
        }

        return authHeader.split(" ")[1];
    }
}