package br.com.fernandes.mocks;

import br.com.fernandes.entities.Agendamento;
import br.com.fernandes.entities.Cliente;
import br.com.fernandes.entities.Servico;

import java.time.LocalDateTime;
import java.util.List;

public class AgendamentosMock {

    public static List<Agendamento> criarListaAgendamentos() {
        Cliente cliente = new Cliente("Gustavo Fernandes", "11958691425", "gustavo@teste.com.br");

        Servico servico1 = new Servico("Corte de cabelo F", "Corte de cabelo Feminino", 80.0);
        Servico servico2 = new Servico("Pé", "Cuidado com os pés", 50.0);
        Servico servico3 = new Servico("Mão", "Cuidado com as mãos", 40.0);

        Agendamento agendamento1 = new Agendamento(
                cliente,
                servico1,
                LocalDateTime.of(2025, 8, 7, 14, 30),
                "Cliente prefere cabelo mais curto nas laterais"
        );

        Agendamento agendamento2 = new Agendamento(
                cliente,
                servico2,
                LocalDateTime.of(2025, 8, 9, 10, 0),
                "Cliente gosta de esmalte claro"
        );

        Agendamento agendamento3 = new Agendamento(
                cliente,
                servico3,
                LocalDateTime.of(2025, 8, 22, 16, 0),
                "Cliente prefere unhas arredondadas"
        );

        return List.of(agendamento1, agendamento2, agendamento3);
    }

    public static Agendamento criarAgendamentoAtualizadoMock() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Gustavo Fernandes");
        cliente.setTelefone("11958691425");
        cliente.setEmail("gustavo@teste.com.br");

        Servico servico = new Servico();
        servico.setId(1L);
        servico.setNome("Corte de Cabelo");
        servico.setPreco(50.0);

        Agendamento agendamento = new Agendamento(
                cliente,
                servico,
                LocalDateTime.of(2025, 10, 22, 14, 0),
                "Primeira vez do cliente"
        );
        agendamento.setId(1L);

        return agendamento;
    }

    public static Agendamento criarAgendamentoMock() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Gustavo Fernandes");
        cliente.setTelefone("11958691425");
        cliente.setEmail("gustavo@teste.com.br");

        Servico servico = new Servico();
        servico.setId(1L);
        servico.setNome("Corte de Cabelo");
        servico.setPreco(50.0);

        Agendamento agendamento = new Agendamento(
                cliente,
                servico,
                LocalDateTime.of(2025, 8, 20, 14, 0),
                "Primeira vez do cliente"
        );
        agendamento.setId(1L);

        return agendamento;
    }
}
