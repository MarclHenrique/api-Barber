package com.barber.apiBarber.Controller;

import com.barber.apiBarber.Model.Agendamento;
import com.barber.apiBarber.Services.AgendamentoService;
import com.barber.apiBarber.Security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/criar")
    public ResponseEntity<?> criarAgendamento(@RequestBody Agendamento request, HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Usuário não autenticado.");
        }

        token = token.substring(7);
        Long usuarioId = jwtUtil.getUsuarioIdDoToken(token);

        if (usuarioId == null) {
            return ResponseEntity.status(401).body("Token inválido.");
        }

        Agendamento agendamento = agendamentoService.criarAgendamento(usuarioId, request.getDataHora(), request.getTipoServico());
        return ResponseEntity.ok(agendamento);
    }

    @GetMapping("/meus-agendamentos")
    public ResponseEntity<List<Agendamento>> listarMeusAgendamentos(HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(null);
        }

        token = token.substring(7);
        Long usuarioId = jwtUtil.getUsuarioIdDoToken(token);

        if (usuarioId == null) {
            return ResponseEntity.status(401).body(null);
        }

        return ResponseEntity.ok(agendamentoService.listarAgendamentosUsuario(usuarioId));
    }
}
