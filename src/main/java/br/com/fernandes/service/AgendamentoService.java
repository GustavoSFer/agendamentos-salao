package br.com.fernandes.service;

import br.com.fernandes.dto.AgendamentoDTO;
import br.com.fernandes.dto.AgendamentoDiaDTO;
import br.com.fernandes.dto.AgendamentosPorClienteDTO;
import br.com.fernandes.entities.Agendamento;
import br.com.fernandes.entities.Cliente;
import br.com.fernandes.entities.Servico;
import br.com.fernandes.exceptions.AgendamentoInvalidoException;
import br.com.fernandes.exceptions.AgendamentoPorClienteException;
import br.com.fernandes.repository.AgendamentoRepository;
import br.com.fernandes.util.AgendamentoDiaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ServicoService servicoService;

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
                        agendamento.getId(),
                        agendamento.getServico().getNome(),
                        agendamento.getServico().getDescricao(),
                        agendamento.getServico().getPreco(),
                        agendamento.getDataHora(),
                        agendamento.getObservacao()
                )).toList();

        return new AgendamentosPorClienteDTO(clienteDTO, agendamentosDTO);
    }

    public Agendamento atualizaAgendamento(Long clienteId, Long agendamentoId, AgendamentoDTO agendamentoDTO) {
        Agendamento agendamentoBanco = agendamentoFindById(agendamentoId);
        Agendamento agendamentoAtualizado = atualizar(agendamentoDTO, agendamentoBanco);

        return agendamentoRepository.save(agendamentoAtualizado);
    }

    private Agendamento atualizar(AgendamentoDTO agendamentoDTO, Agendamento agendamentoBanco) {
        agendamentoBanco.setDataHora(agendamentoDTO.dataHora());
        agendamentoBanco.setObservacao(agendamentoDTO.observacao());
        Cliente cliente = clienteService.buscaClientePeloId(agendamentoDTO.clienteId());
        agendamentoBanco.setCliente(cliente);
        Servico servico = servicoService.buscarServicoPeloId(agendamentoDTO.servicoId());
        agendamentoBanco.setServico(servico);

        return agendamentoBanco;
    }

    private Agendamento agendamentoFindById(Long id) {
        Optional<Agendamento> agendamento = agendamentoRepository.findById(id);

        return agendamento.orElseThrow(() -> new AgendamentoPorClienteException("Agendamento não encontrado!"));
    }

    public void removerAgendamento(Long id) {
        Agendamento agendamento = agendamentoFindById(id);

        agendamentoRepository.delete(agendamento);
    }

    public AgendamentoDiaDTO agendamentoDia(LocalDate data) {
        LocalDateTime inicio = data.atStartOfDay();
        LocalDateTime fim = data.atTime(LocalTime.MAX);
        AgendamentoDiaDTO agendamentoDiaDTO = new AgendamentoDiaDTO();

        List<Agendamento> listaAgendamento = agendamentoRepository.findByDataHoraBetween(inicio, fim);

        List<AgendamentoDiaDTO.AgendamentoData> listaAgendamentoDia = listaAgendamento.stream().map(AgendamentoDiaMapper::AgendamentoToAgendamentoDia).toList();
        agendamentoDiaDTO.setData(listaAgendamentoDia);

        return agendamentoDiaDTO;
    }
}
