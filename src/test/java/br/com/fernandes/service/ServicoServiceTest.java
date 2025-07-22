package br.com.fernandes.service;

import br.com.fernandes.entities.Servico;
import br.com.fernandes.repository.ServicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

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
    @DisplayName("Deve ser retornado a lista de serviços ordenado pelo nome")
    void testeRetornarListaServicoOrdenado() {
        List<Servico> servicos = new ArrayList<>();
        servicos.add(new Servico("Pezinho", 20.00D));
        servicos.add(new Servico("Corte de cabelo", 80.00D));

        when(servicoRepository.findAll()).thenReturn(servicos);

        List<Servico> listaServicos = servicoService.listarServicos();

        assertEquals("Corte de cabelo", listaServicos.get(0).getNome());
        assertEquals(80D, listaServicos.get(0).getPreco());
        assertEquals(2, listaServicos.size());
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia quando nao tiver serviços.")
    void testeRetornarListaVazia() {
        List<Servico> servicos = new ArrayList<>();

        when(servicoRepository.findAll()).thenReturn(servicos);

        List<Servico> listaServicos = servicoService.listarServicos();

        assertEquals(0, listaServicos.size());
    }
}