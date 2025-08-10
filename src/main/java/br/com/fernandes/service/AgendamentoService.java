package br.com.fernandes.service;

import br.com.fernandes.dto.AgendamentosPorClienteDTO;
import br.com.fernandes.entities.Agendamento;
import br.com.fernandes.entities.Cliente;
import br.com.fernandes.exceptions.AgendamentoInvalidoException;
import br.com.fernandes.exceptions.AgendamentoPorClienteException;
import br.com.fernandes.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Agendamento criarAgendamento(Agendamento agendamento) {
        if (!agendamento.getDataHora().isAfter(LocalDateTime.now())) {
            throw new AgendamentoInvalidoException("Dados informado para o agendamento está invalido, por favor revise!");
        }
        return agendamentoRepository.save(agendamento);
    }

    public AgendamentosPorClienteDTO listaAgendamentos(Long clienteId) {
        List<Agendamento> agendamentos = agendamentoRepository.findByClienteId(clienteId);

        if (agendamentos.isEmpty()) {
            throw new AgendamentoPorClienteException("Cliente não possui agendamentos");
        }
        Cliente cliente = agendamentos.get(0).getCliente();
        var clienteDTO = new AgendamentosPorClienteDTO.ClienteDTO(
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail()
        );

        var agendamentosDTO = agendamentos.stream().map(
                agendamento -> new AgendamentosPorClienteDTO.AgendamentoSimplesDTO(
                        new AgendamentosPorClienteDTO.ServicoDTO(
                                agendamento.getServico().getNome(),
                                agendamento.getServico().getDescricao(),
                                agendamento.getServico().getPreco()
                        ),
                        agendamento.getDataHora(),
                        agendamento.getObservacao()
                )).toList();

        return new AgendamentosPorClienteDTO(clienteDTO, agendamentosDTO);
    }

}
