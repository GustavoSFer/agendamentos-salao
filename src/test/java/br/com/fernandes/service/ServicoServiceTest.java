package br.com.fernandes.service;

import br.com.fernandes.entities.Servico;
import br.com.fernandes.repository.ServicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
}