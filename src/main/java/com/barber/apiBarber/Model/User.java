package com.barber.apiBarber.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // CLIENTE ou BARBEIRO

    private Boolean atendeDomicilio; // Apenas para barbeiros

    private String servicos; // Serviços oferecidos

    private String contato;
}
