package com.barber.apiBarber.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "agendamentos")
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

    public Agendamento() {
    }

    public Agendamento(Long id, User user, LocalDateTime dataHora, String tipoServico, Boolean confirmado) {
        this.id = id;
        this.user = user;
        this.dataHora = dataHora;
        this.tipoServico = tipoServico;
        this.confirmado = confirmado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Agendamento that = (Agendamento) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}