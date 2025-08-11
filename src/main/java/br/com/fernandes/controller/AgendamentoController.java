package br.com.fernandes.controller;

import br.com.fernandes.dto.AgendamentoDTO;
import br.com.fernandes.dto.AgendamentosPorClienteDTO;
import br.com.fernandes.entities.Agendamento;
import br.com.fernandes.entities.Cliente;
import br.com.fernandes.entities.Servico;
import br.com.fernandes.service.AgendamentoService;
import br.com.fernandes.service.ClienteService;
import br.com.fernandes.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public ResponseEntity<Agendamento> criarAgendamento(@Valid @RequestBody AgendamentoDTO agendamentoDto) {
        Cliente cliente = clienteService.buscaClientePeloId(agendamentoDto.clienteId());
        Servico servico = servicoService.buscarServicoPeloId(agendamentoDto.servicoId());

        Agendamento agendamento = new Agendamento(cliente, servico, agendamentoDto.dataHora(), agendamentoDto.observacao());
        Agendamento agendamentoCriado = agendamentoService.criarAgendamento(agendamento);

        return ResponseEntity.ok().body(agendamentoCriado);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<AgendamentosPorClienteDTO> listarAgendamentos(@PathVariable Long clienteId) {
        AgendamentosPorClienteDTO agendamentos = agendamentoService.listaAgendamentos(clienteId);

        return ResponseEntity.ok().body(agendamentos);
    }

    @PutMapping("/{clienteId}/agendamento/{agendamentoId}")
    public ResponseEntity<Agendamento> atualizaAgendamento(
            @PathVariable Long clienteId,
            @PathVariable Long agendamentoId,
            @RequestBody AgendamentoDTO agendamentoDTO
    ) {
        Agendamento agendamento = agendamentoService.atualizaAgendamento(clienteId, agendamentoId, agendamentoDTO);

        return ResponseEntity.ok().body(agendamento);
    }

}
