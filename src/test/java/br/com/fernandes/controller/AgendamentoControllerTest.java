package br.com.fernandes.controller;

import br.com.fernandes.dto.AgendamentoDTO;
import br.com.fernandes.dto.AgendamentosPorClienteDTO;
import br.com.fernandes.entities.Agendamento;
import br.com.fernandes.entities.Cliente;
import br.com.fernandes.entities.Servico;
import br.com.fernandes.mocks.AgendamentosPorClienteMock;
import br.com.fernandes.service.AgendamentoService;
import br.com.fernandes.service.ClienteService;
import br.com.fernandes.service.ServicoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AgendamentoController.class)
class AgendamentoControllerTest {

    @MockBean
    AgendamentoService agendamentoService;

    @MockBean
    ClienteService clienteService;

    @MockBean
    ServicoService servicoService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private String PATH = "/agendamentos";

    private Cliente cliente = new Cliente();
    private Servico servico = new Servico();
    private Agendamento agendamento = new Agendamento(cliente, servico, LocalDateTime.now(), "Corte de cabelo");

    @BeforeEach
    public void setUp() {
        cliente.setId(1L);
        cliente.setNome("Gustavo");
        cliente.setEmail("gustavo@gu.com.br");
        cliente.setTelefone("11985891547");

        servico.setId(3L);
        servico.setNome("Corte de cabelo");
        servico.setDescricao("Masculino");
        servico.setPreco(80.0D);
    }

    @Test
    @DisplayName("Deve ser possivel criar um agendamento com sucesso.")
    void criarAgendamentoTest() throws Exception {
        AgendamentoDTO agendamentoDTO = new AgendamentoDTO(1L, 3L, LocalDateTime.now().plusDays(3), "Corte de cabelo");
        Agendamento agendamentoCriado = new Agendamento();
        agendamentoCriado.setId(1L);
        agendamentoCriado.setCliente(cliente);
        agendamentoCriado.setServico(servico);
        agendamentoCriado.setObservacao(agendamentoDTO.observacao());
        agendamentoCriado.setDataHora(agendamentoDTO.dataHora());

        when(clienteService.buscaClientePeloId(agendamentoDTO.clienteId())).thenReturn(cliente);
        when(servicoService.buscarServicoPeloId(agendamentoDTO.servicoId())).thenReturn(servico);
        when(agendamentoService.criarAgendamento(agendamento)).thenReturn(agendamentoCriado);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(agendamentoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @DisplayName("Deve retornar a lista de agendamentos de um cliente")
    void testListarAgendamentoCliente() throws Exception {
        Long clienteId = 1L;
        AgendamentosPorClienteDTO mock = AgendamentosPorClienteMock.criarMock();

        when(agendamentoService.listaAgendamentos(clienteId)).thenReturn(mock);

        mockMvc.perform(get(PATH + "/" + clienteId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cliente.nome").value(mock.getCliente().getNome()))
                .andExpect(jsonPath("$.cliente.email").value(mock.getCliente().getEmail()))
                .andExpect(jsonPath("$.cliente.telefone").value(mock.getCliente().getTelefone()))
                .andExpect(jsonPath("$.agendamentos.size()").value(mock.getAgendamentos().size()));
    }

    @Test
    @DisplayName("Deve ser possivel atualizar um agendamento de um cliente")
    void testAtualizarAgendamentoCliente() throws Exception {
        Long clienteId = 1L;
        Long agendamentoId = 1L;
        Long servicoId = 3L;

        AgendamentoDTO agendamentoDTO = new AgendamentoDTO(clienteId, servicoId, LocalDateTime.now(), "Atual");
        Agendamento agendamento = new Agendamento(
                new Cliente(1l, "Gusta", "958962366", "gus@gmail.com"),
                new Servico("corte", "coste tipo 1", 120.0D),
                LocalDateTime.now(),
                "Agendamento atual"
        );

        when(agendamentoService.atualizaAgendamento(clienteId, agendamentoId, agendamentoDTO)).thenReturn(agendamento);

        mockMvc.perform(put(PATH + "/" + clienteId + "/agendamento/" + agendamentoId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(agendamentoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cliente.id").value(1))
                .andExpect(jsonPath("$.servico").isNotEmpty());

    }

}