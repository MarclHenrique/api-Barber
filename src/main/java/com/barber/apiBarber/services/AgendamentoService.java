package com.barber.apiBarber.services;

import com.barber.apiBarber.model.Agendamento;
import com.barber.apiBarber.model.User;
import com.barber.apiBarber.Repository.AgendamentoRepository;
import com.barber.apiBarber.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private UserRepository userRepository;

    public Agendamento criarAgendamento(Long userId, LocalDateTime dataHora, String tipoServico) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setUser(user.get());
        agendamento.setDataHora(dataHora);
        agendamento.setTipoServico(tipoServico);
        agendamento.setConfirmado(false);

        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarAgendamentosUsuario(Long userId) {
        return agendamentoRepository.findByUserId(userId);
    }
}
