package br.com.fernandes.controller;

import br.com.fernandes.dto.ClienteDTO;
import br.com.fernandes.entities.Cliente;
import br.com.fernandes.service.ClienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    @DisplayName("Deve ser criado um cliente com sucesso.")
    void criarClienteTest() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO("Gustavo Fernandes", "11856952245", "gustavo@teste.com");
        Cliente clienteCriado = new Cliente();
        clienteCriado.setId(1L);
        clienteCriado.setNome(clienteDTO.nome());
        clienteCriado.setTelefone(clienteDTO.telefone());
        clienteCriado.setEmail(clienteDTO.email());

        when(clienteService.crairCliente(any(Cliente.class))).thenReturn(clienteCriado);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Gustavo Fernandes"));
    }

    @Test
    @DisplayName("Deve retornar 400 quando falta algum parametro no body")
    public void deveRetornarFalhaComBodyErradoTest() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO("Gustavo Fernandes", "11856952245", null);

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isBadRequest());

    }
}
