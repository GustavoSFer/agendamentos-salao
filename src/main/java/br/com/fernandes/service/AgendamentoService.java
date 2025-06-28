package br.com.fernandes.service;

import br.com.fernandes.entities.Agendamento;
import br.com.fernandes.exceptions.AgendamentoInvalidoException;
import br.com.fernandes.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Agendamento criarAgendamento(Agendamento agendamento) {
        if (!agendamento.getDataHora().isAfter(LocalDateTime.now())) {
            throw new AgendamentoInvalidoException("Dados informado para o agendamento está invalido, por favor revise!");
        }
        return agendamentoRepository.save(agendamento);
    }
}
