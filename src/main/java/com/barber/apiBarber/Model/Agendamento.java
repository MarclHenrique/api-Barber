package com.barber.apiBarber.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Cliente que fez o agendamento

    @Column(nullable = false)
    private LocalDateTime dataHora; // Data e hora do agendamento

    @Column(nullable = false)
    private String tipoServico; // Tipo de serviço escolhido pelo cliente

    private Boolean confirmado = false; // Status do agendamento (confirmado ou não)
}
