package br.com.fernandes.service;

import br.com.fernandes.entities.Servico;
import br.com.fernandes.exceptions.ServicoNotFoundException;
import br.com.fernandes.repository.ServicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    @DisplayName("Deve retornar uma exception quando não tiver o servico informado pelo id")
    void testDeveRetornarExceptionServicoNaoEncontrado() {
        Servico servico = new Servico();
        servico.setId(1L);
        servico.setNome("Corte de cabelo masculino");
        servico.setPreco(60.00D);

        Exception error = assertThrows(ServicoNotFoundException.class, () ->
                servicoService.buscarServicoPeloId(25L));

        assertEquals("Serviço informado não encontrado.", error.getMessage());
        verify(servicoRepository, times(1)).findById(25L);
    }

    @Test
    @DisplayName("Deve ser possivel deletar um servico.")
    void testDeletarServico() {
        Servico servico = new Servico();
        servico.setId(1L);
        servico.setNome("Corte de cabelo masculino");
        servico.setPreco(60.00D);

        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));
        doNothing().when(servicoRepository).delete(servico);

        servicoService.deletarServico(1L);

        verify(servicoRepository, times(1)).delete(servico);
    }

    @Test
    @DisplayName("Deve ser possivel atualizar um servico")
    void testAtualizandoUmServico() {
        Servico servicoAntigo = new Servico();
        servicoAntigo.setId(1L);
        servicoAntigo.setNome("Corte de cabelo masculino");
        servicoAntigo.setPreco(60.00D);

        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servicoAntigo));
        when(servicoRepository.save(any(Servico.class))).thenReturn(servicoAntigo);

        Servico servicoAtualizado = servicoService.atualizarServico(servicoAntigo, 1L);

        assertEquals("Corte de cabelo masculino", servicoAtualizado.getNome());
        assertEquals(60.00D, servicoAtualizado.getPreco());
    }

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