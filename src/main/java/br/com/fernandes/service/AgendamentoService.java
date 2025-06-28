package br.com.fernandes.service;

import br.com.fernandes.entities.Agendamento;
import br.com.fernandes.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Agendamento criarAgendamento(Agendamento agendamento) {
        if (!agendamento.getDataHora().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Informe a data e hora corretamente");
        }
        return agendamentoRepository.save(agendamento);
    }
}
