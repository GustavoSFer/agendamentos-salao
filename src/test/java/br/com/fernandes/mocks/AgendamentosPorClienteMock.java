package br.com.fernandes.mocks;

import br.com.fernandes.dto.AgendamentosPorClienteDTO;

import java.time.LocalDateTime;
import java.util.List;

public class AgendamentosPorClienteMock {

    public static AgendamentosPorClienteDTO criarMock() {
        AgendamentosPorClienteDTO.ClienteDTO cliente = new AgendamentosPorClienteDTO.ClienteDTO(
                "Gustavo Fernandes",
                "11958691425",
                "gustavo@teste.com.br"
        );

        List<AgendamentosPorClienteDTO.AgendamentoSimplesDTO> agendamentos = List.of(
                new AgendamentosPorClienteDTO.AgendamentoSimplesDTO(
                        2L,
                        "Corte de cabelo F",
                        "Corte de cabelo Feminino",
                        80.0,
                        LocalDateTime.of(2025, 8, 7, 14, 30),
                        "Cliente prefere cabelo mais curto nas laterais"
                ),
                new AgendamentosPorClienteDTO.AgendamentoSimplesDTO(
                        3L,
                        "Corte de cabelo F",
                        "Corte de cabelo Feminino",
                        80.0,
                        LocalDateTime.of(2025, 8, 22, 14, 30),
                        "Cliente legal"
                ),
                new AgendamentosPorClienteDTO.AgendamentoSimplesDTO(
                        4L,
                        "Corte de cabelo F",
                        "Corte de cabelo Feminino",
                        80.0,
                        LocalDateTime.of(2025, 8, 9, 14, 30),
                        "Cliente prefere cabelo mais curto nas laterais"
                ),
                new AgendamentosPorClienteDTO.AgendamentoSimplesDTO(
                        5L,
                        "Pé",
                        "Pé",
                        80.0,
                        LocalDateTime.of(2025, 8, 9, 14, 30),
                        "Cliente prefere cabelo mais curto nas laterais"
                )
        );

        return new AgendamentosPorClienteDTO(cliente, agendamentos);
    }
}
