package br.com.fernandes.service;

import br.com.fernandes.entities.Agendamento;
import br.com.fernandes.entities.Cliente;
import br.com.fernandes.entities.Servico;
import br.com.fernandes.exceptions.AgendamentoInvalidoException;
import br.com.fernandes.repository.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AgendamentoServiceTest {

    @InjectMocks
    AgendamentoService agendamentoService;

    @Mock
    AgendamentoRepository agendamentoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Deve ser possivel realizar um  agendamento com sucesso")
    void criarAgendamentoTest() {
        Agendamento agendamento = new Agendamento(
                new Cliente("Gustavo", "11958965847", "gustavo@gmail.com"),
                new Servico("Corte cabelo", "corte cabelo", 60D),
                LocalDateTime.now().plusDays(2), "Cliente gosta de corte de cria.");

        when(agendamentoRepository.save(agendamento)).thenReturn(agendamento);

        Agendamento agendamentoCriado = agendamentoService.criarAgendamento(agendamento);

        assertEquals(agendamento.getCliente().getNome(), agendamentoCriado.getCliente().getNome());
    }

    @Test
    @DisplayName("Deve retornar a mensagem 'Dados informado para o agendamento está invalido, por favor revise!', quando passando informacoes inconsistentes")
    public void testeDadosInvalido() {
        Agendamento agendamento = new Agendamento(
                new Cliente("Gustavo", "11958965847", "gustavo@gmail.com"),
                new Servico("Corte cabelo", "corte cabelo", 60D),
                LocalDateTime.now().plusDays(-2), "Cliente gosta de corte de cria.");

        Exception error = assertThrows(AgendamentoInvalidoException.class, () -> {
            agendamentoService.criarAgendamento(agendamento);
        });

        assertEquals("Dados informado para o agendamento está invalido, por favor revise!", error.getMessage());
        verify(agendamentoRepository, never()).save(any(Agendamento.class));

    }
}