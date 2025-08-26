package br.com.fernandes.util;

import br.com.fernandes.dto.AgendamentoDiaDTO;
import br.com.fernandes.entities.Agendamento;

import java.time.LocalDate;

public class AgendamentoDiaMapper {

    public static AgendamentoDiaDTO.AgendamentoData AgendamentoToAgendamentoDia(Agendamento agendamento) {

        AgendamentoDiaDTO.AgendamentoData agendamentoData = new AgendamentoDiaDTO.AgendamentoData();
        agendamentoData.setClienteNome(agendamento.getCliente().getNome());
        agendamentoData.setEmail(agendamento.getCliente().getEmail());
        agendamentoData.setTelefone(agendamento.getCliente().getTelefone());
        agendamentoData.setServico(agendamento.getServico().getNome());
        agendamentoData.setPreco(agendamento.getServico().getPreco());
        LocalDate date = agendamento.getDataHora().toLocalDate();
        agendamentoData.setData(date);

        return agendamentoData;
    }
}
