package br.com.fernandes.controller;

import br.com.fernandes.dto.AgendamentoDTO;
import br.com.fernandes.entities.Agendamento;
import br.com.fernandes.service.AgendamentoService;
import br.com.fernandes.util.AgendamentoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<Agendamento> criarAgendamento(@Valid @RequestBody AgendamentoDTO agendamentoDto) {
        // Criar busca de cliente pelo id na service

        // Criar busca do servico pelo id na service

        Agendamento agendamento = AgendamentoMapper.agendamentoDtoToAgendamento(agendamentoDto);
        Agendamento agendamentoCriado = agendamentoService.criarAgendamento(agendamento);

        return ResponseEntity.ok().body(agendamentoCriado);
    }
}
