package br.com.fernandes.controller;

import br.com.fernandes.dto.ServicoDTO;
import br.com.fernandes.entities.Servico;
import br.com.fernandes.service.ServicoService;
import br.com.fernandes.util.ServicoMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServicoController.class)
class ServicoControllerTest {

    @MockBean
    ServicoService servicoService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve ser criado um serviço com sucesso")
    void testCriarServico() throws Exception {
        ServicoDTO servicoDto = new ServicoDTO("Corte cabelo masculino", "Corte de cabelo", 40.00D);
        Servico servico = new Servico("Corte cabelo masculino", "Corte de cabelo", 40.00D);

        when(servicoService.criarServico(any(Servico.class))).thenReturn(servico);

        mockMvc.perform(post("/servicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicoDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Corte cabelo masculino"))
                .andExpect(jsonPath("$.descricao").value(servico.getDescricao()))
                .andExpect(jsonPath("$.preco").value(servico.getPreco()));
    }

    @Test
    @DisplayName("Dese ser possivel cadastrar um serviço sem a descrição")
    void testCadastrarServicoSemDescricao() throws Exception {
        ServicoDTO servicoDto = new ServicoDTO("Corte cabelo masculino", null,40.00D);
        Servico servico = new Servico("Corte cabelo masculino", 40.00D);

        when(servicoService.criarServico(any(Servico.class))).thenReturn(servico);

        mockMvc.perform(post("/servicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicoDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(servico.getNome()))
                .andExpect(jsonPath("$.descricao").value(nullValue()))
                .andExpect(jsonPath("$.preco").value(servico.getPreco()));
    }
}