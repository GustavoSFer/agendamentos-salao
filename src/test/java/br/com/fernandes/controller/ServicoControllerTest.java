package br.com.fernandes.controller;

import br.com.fernandes.dto.ServicoDTO;
import br.com.fernandes.entities.Servico;
import br.com.fernandes.service.ServicoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    private final String PATH = "/servicos";

    @Test
    @DisplayName("Deve ser criado um serviço com sucesso")
    void testCriarServico() throws Exception {
        ServicoDTO servicoDto = new ServicoDTO("Corte cabelo masculino", "Corte de cabelo", 40.00D);
        Servico servico = new Servico("Corte cabelo masculino", "Corte de cabelo", 40.00D);

        when(servicoService.criarServico(any(Servico.class))).thenReturn(servico);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicoDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Corte cabelo masculino"))
                .andExpect(jsonPath("$.descricao").value(servico.getDescricao()))
                .andExpect(jsonPath("$.preco").value(servico.getPreco()));
    }

    @Test
    @DisplayName("Deve ser possível cadastrar um serviço sem a descrição")
    void testCadastrarServicoSemDescricao() throws Exception {
        ServicoDTO servicoDto = new ServicoDTO("Corte cabelo masculino", null,40.00D);
        Servico servico = new Servico("Corte cabelo masculino", 40.00D);

        when(servicoService.criarServico(any(Servico.class))).thenReturn(servico);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicoDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(servico.getNome()))
                .andExpect(jsonPath("$.descricao").value(nullValue()))
                .andExpect(jsonPath("$.preco").value(servico.getPreco()));
    }

    @Test
    @DisplayName("Deve retornar status 400 quando não é informado nome ou preco.")
    void testErrorAoEnviarDadosInvalidos() throws Exception {
        ServicoDTO servicoDTO = new ServicoDTO(null, null, 60.00D);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicoDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar status 400 quando não é informado nome ou preco.")
    void testErrorAoEnviarprecoInvalidos() throws Exception {
        ServicoDTO servicoDTO = new ServicoDTO("Corte cabelo", null, 0.00D);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(servicoDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar a lista de serviços.")
    void testeDeveRetornarListaServicos() throws Exception {
        List<Servico> servicos = new ArrayList<>();
        servicos.add(new Servico("Pezinho", 20.00D));
        servicos.add(new Servico("Corte de cabelo", 80.00D));

        when(servicoService.listarServicos()).thenReturn(servicos);

        mockMvc.perform(get(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicos)))
                .andExpect(jsonPath("$[0].nome").value(servicos.get(0).getNome()))
                .andExpect(jsonPath("$[0].preco").value(servicos.get(0).getPreco()))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar uma lista de serviços vazio.")
    void testeDeveRetornarListaServicosvazio() throws Exception {
        List<Servico> servicos = new ArrayList<>();

        when(servicoService.listarServicos()).thenReturn(servicos);

        mockMvc.perform(get(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(servicos)))
                .andExpect(jsonPath("$.length()").value(0))
                .andExpect(status().isOk());
    }
}