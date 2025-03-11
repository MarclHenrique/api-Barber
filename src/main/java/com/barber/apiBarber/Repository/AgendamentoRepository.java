package com.barber.apiBarber.Repository;

import com.barber.apiBarber.Model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByUserId(Long userId);
}
