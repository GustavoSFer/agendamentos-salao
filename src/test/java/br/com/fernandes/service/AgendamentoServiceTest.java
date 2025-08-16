package br.com.fernandes.service;

import br.com.fernandes.dto.AgendamentoDTO;
import br.com.fernandes.dto.AgendamentosPorClienteDTO;
import br.com.fernandes.entities.Agendamento;
import br.com.fernandes.entities.Cliente;
import br.com.fernandes.entities.Servico;
import br.com.fernandes.exceptions.AgendamentoInvalidoException;
import br.com.fernandes.exceptions.AgendamentoPorClienteException;
import br.com.fernandes.mocks.AgendamentosMock;
import br.com.fernandes.mocks.AgendamentosPorClienteMock;
import br.com.fernandes.repository.AgendamentoRepository;
import br.com.fernandes.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgendamentoServiceTest {

    @InjectMocks
    AgendamentoService agendamentoService;

    @Mock
    AgendamentoRepository agendamentoRepository;

    @Mock
    private ClienteService clienteService;

    @Mock
    private ServicoService servicoService;

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

    @Test
    @DisplayName("Deve retornar uma lista de agendamentos por cliente")
    void testRetornaListaAgendamentoPorCliente() {
        List<Agendamento> agendamentos = AgendamentosMock.criarListaAgendamentos();
        AgendamentosPorClienteDTO agendamentosPorClienteDTO = AgendamentosPorClienteMock.criarMock();

        when(agendamentoRepository.findByClienteId(1L)).thenReturn(agendamentos);

        AgendamentosPorClienteDTO result = agendamentoService.listaAgendamentos(1L);

        assertNotNull(result.getAgendamentos());
        assertNotNull(result.getCliente());
        assertEquals(result.getCliente().getNome(), agendamentosPorClienteDTO.getCliente().getNome());
    }

    @Test
    @DisplayName("Deve retornar uma exception Cliente não possui agendamentos")
    void testDeveRetornarExceptionNaoExisteAgendamento() {
        List<Agendamento> agendamentoVazio = new ArrayList<>();

        when(agendamentoRepository.findByClienteId(4L)).thenReturn(agendamentoVazio);

        Exception error = assertThrows(AgendamentoPorClienteException.class, () ->
                agendamentoService.listaAgendamentos(4L)
                );

        assertEquals("Cliente não possui agendamentos", error.getMessage());
        verify(agendamentoRepository, times(1)).findByClienteId(4L);
    }

    @Test
    @DisplayName("Deve ser possivel atualizar um agendamento")
    void testAtualizarAgendamento() {
        Agendamento agendamentoBanco = AgendamentosMock.criarAgendamentoMock();
        Agendamento agendamentoAtualizado = AgendamentosMock.criarAgendamentoAtualizadoMock();
        AgendamentoDTO agendamentoDTO = new AgendamentoDTO(1L, 1L, LocalDateTime.now(), "teste");

        Cliente cliente = new Cliente("Gusta", "11958889541", "gusta@gmail.com");
        Servico servico = new Servico("Corte de Cabelo", "Corte simples", 50.00);

        when(agendamentoRepository.findById(1L)).thenReturn(Optional.of(agendamentoBanco));
        when(clienteService.buscaClientePeloId(1L)).thenReturn(cliente);
        when(servicoService.buscarServicoPeloId(1L)).thenReturn(servico);
        when(agendamentoRepository.save(any(Agendamento.class))).thenReturn(agendamentoAtualizado);

        Agendamento result = agendamentoService.atualizaAgendamento(1L, 1L, agendamentoDTO);

        assertNotNull(result);
        assertEquals(agendamentoAtualizado.getDataHora(), result.getDataHora());
    }

    @Test
    @DisplayName("Deve retornar Agendamento não encontrado! quando não existir")
    void testRetornarAgendamentoPeloId() {
        Long clienteId = 1L;
        Long agendamentoId = 1L;
        AgendamentoDTO agendamentoDTO = new AgendamentoDTO(1L, 1L, LocalDateTime.now(), "teste");


        Exception error = assertThrows(AgendamentoPorClienteException.class, () ->
                agendamentoService.atualizaAgendamento(clienteId, agendamentoId, agendamentoDTO)
                );
        assertEquals("Agendamento não encontrado!", error.getMessage());
   }


}