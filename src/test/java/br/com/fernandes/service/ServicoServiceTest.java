package br.com.fernandes.service;

import br.com.fernandes.entities.Servico;
import br.com.fernandes.repository.ServicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ServicoServiceTest {

    @Mock
    ServicoRepository servicoRepository;

    @InjectMocks
    ServicoService servicoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve criar um serviço com sucesso")
    void testCriarServico() {
        Servico servico = new Servico("Corte de cabelo", "Corte de cabelo masculino", 60D);

        Servico servicoCriado = new Servico();
        servicoCriado.setId(1L);
        servicoCriado.setNome(servico.getNome());
        servicoCriado.setDescricao(servico.getDescricao());
        servicoCriado.setPreco(servico.getPreco());

        when(servicoRepository.save(servico)).thenReturn(servicoCriado);

        Servico response = servicoService.criarServico(servico);

        assertNotNull(response);
        assertEquals(servico.getNome(), response.getNome());
        assertEquals(1L, response.getId());
        assertEquals(servico.getPreco(), response.getPreco());
    }

    @Test
    @DisplayName("Deve ser possivel cadastrar um serviço sem a descrição")
    void testeCadastrarServicoSemDescricao() {
        Servico servico = new Servico("Corte de cabelo", 60D);

        Servico servicoCriado = new Servico();
        servicoCriado.setId(1L);
        servicoCriado.setNome(servico.getNome());
        servicoCriado.setPreco(servico.getPreco());

        when(servicoRepository.save(servico)).thenReturn(servicoCriado);

        Servico response = servicoService.criarServico(servico);

        assertNotNull(response);
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getDescricao()).isBlank();
        assertThat(response.getNome()).isEqualTo(servico.getNome());
    }

    @Test
    @DisplayName("Deve retornar um serviço buscando pelo id 1")
    void testBuscarServicoPeloId() {
        Servico servico = new Servico();
        servico.setId(1L);
        servico.setNome("Corte de cabelo masculino");
        servico.setPreco(60.00D);

        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));

        Servico servicoRetornado = servicoService.buscarServicoPeloId(1L);

        assertEquals(servico.getNome(), servicoRetornado.getNome());
        assertEquals(servico.getDescricao(), servicoRetornado.getDescricao());
        assertEquals(servico.getPreco(), servicoRetornado.getPreco());
        assertEquals(servico.getId(), servicoRetornado.getId());
    }
}