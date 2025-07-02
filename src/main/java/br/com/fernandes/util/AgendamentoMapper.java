package br.com.fernandes.util;

import br.com.fernandes.dto.AgendamentoDTO;
import br.com.fernandes.entities.Agendamento;

public class AgendamentoMapper {

    public static Agendamento agendamentoDtoToAgendamento(AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(agendamentoDTO.cliente());
        agendamento.setServico(agendamentoDTO.servico());
        agendamento.setDataHora(agendamentoDTO.dataHora());
        agendamento.setObservacao(agendamentoDTO.observacao());

        return agendamento;
    }
}
