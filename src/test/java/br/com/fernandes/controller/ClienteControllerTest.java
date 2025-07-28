package br.com.fernandes.controller;

import br.com.fernandes.dto.ClienteDTO;
import br.com.fernandes.entities.Cliente;
import br.com.fernandes.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @MockBean
    ClienteService clienteService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    private final String PATH = "/clientes";

    @Test
    @DisplayName("Deve ser criado um cliente com sucesso.")
    void criarClienteTest() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO("Gustavo Fernandes", "11856952245", "gustavo@teste.com");
        Cliente clienteCriado = new Cliente();
        clienteCriado.setId(1L);
        clienteCriado.setNome(clienteDTO.nome());
        clienteCriado.setTelefone(clienteDTO.telefone());
        clienteCriado.setEmail(clienteDTO.email());

        when(clienteService.criarCliente(any(Cliente.class))).thenReturn(clienteCriado);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Gustavo Fernandes"));
    }

    @Test
    @DisplayName("Deve retornar 400 quando falta algum parametro no body")
    public void deveRetornarFalhaComBodyErradoTest() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO("Gustavo Fernandes", "11856952245", null);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um cliente quando passando um id do cliente")
    public void testRetornarClientePeloId() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setTelefone("11965981152");
        cliente.setNome("Gustavo Fer");
        cliente.setEmail("gustavo@gmail.com");

        when(clienteService.buscaClientePeloId(1L)).thenReturn(cliente);

        mockMvc.perform(get(PATH + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Gustavo Fer"))
                .andExpect(jsonPath("$.email").value("gustavo@gmail.com"));
    }

    @Test
    @DisplayName("Deve retornar uma lista de clientes")
    void testRetornaListaClientes() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setTelefone("11965981152");
        cliente.setNome("Gustavo Fer");
        cliente.setEmail("gustavo@gmail.com");

        Cliente cliente2 = new Cliente();
        cliente2.setId(1L);
        cliente2.setTelefone("119600081199");
        cliente2.setNome("Amanda Silva");
        cliente2.setEmail("amandinha@gmail.com");

        List<Cliente> clientes = Arrays.asList(cliente, cliente2);

        when(clienteService.listarClientes()).thenReturn(clientes);

        mockMvc.perform(get(PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].email").value("gustavo@gmail.com"));
    }

    @Test
    @DisplayName("Deve retornar um array vazio quando não existir clientes")
    void testRetornaArrayVazio() throws Exception {
        List<Cliente> clientes = new ArrayList<>();

        when(clienteService.listarClientes()).thenReturn(clientes);

        mockMvc.perform(get(PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));
    }

    @Test
    @DisplayName("Deve ser possivel atualizar um cliente")
    void testAtualizarCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setTelefone("11965981152");
        cliente.setNome("Gustavo Fernandes");
        cliente.setEmail("gustavoF@gmail.com");

        when(clienteService.atualizaCliente(cliente, 1L)).thenReturn(cliente);

        mockMvc.perform(put(PATH + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Gustavo Fernandes"))
                .andExpect(jsonPath("$.email").value("gustavoF@gmail.com"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("Deve ser possivel deletar um cliente")
    void testDeletarCliente() throws Exception {
        doNothing().when(clienteService).deleteCliente(1L);

        mockMvc.perform(delete(PATH + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
