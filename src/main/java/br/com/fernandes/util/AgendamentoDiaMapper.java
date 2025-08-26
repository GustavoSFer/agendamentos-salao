package br.com.fernandes.util;

import br.com.fernandes.dto.AgendamentoDiaDTO;
import br.com.fernandes.entities.Agendamento;

import java.time.ZoneId;
import java.util.Date;

public class AgendamentoDiaMapper {

    public static AgendamentoDiaDTO AgendamentoToAgendamentoDia(Agendamento agendamento) {
        AgendamentoDiaDTO agendamentoDiaDTO = new AgendamentoDiaDTO();

        AgendamentoDiaDTO.AgendamentoData agendamentoData = new AgendamentoDiaDTO.AgendamentoData();
        agendamentoData.setClienteNome(agendamento.getCliente().getNome());
        agendamentoData.setEmail(agendamento.getCliente().getEmail());
        agendamentoData.setTelefone(agendamento.getCliente().getTelefone());
        agendamentoData.setServico(agendamento.getServico().getNome());
        agendamentoData.setPreco(agendamento.getServico().getPreco());
        Date date = Date.from(agendamento.getDataHora()
                .atZone(ZoneId.systemDefault())
                .toInstant());
        agendamentoData.setData(date);

        agendamentoDiaDTO.setData(agendamentoData);

        return agendamentoDiaDTO;
    }
}
