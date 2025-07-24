package br.com.fernandes.service;

import br.com.fernandes.entities.Cliente;
import br.com.fernandes.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class ClienteServiceTest {

    @Mock
    ClienteRepository clienteRepository;

    @InjectMocks
    ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve ser salvo um cliente com sucesso.")
    void crairClienteComSucessoTest() {
        Cliente cliente = new Cliente("Gustavo Fernandes", "958692245", "gustavo@teste.com");

        Cliente clienteSalvo = new Cliente();
        clienteSalvo.setId(1L);
        clienteSalvo.setNome(cliente.getNome());
        clienteSalvo.setTelefone(cliente.getTelefone());
        clienteSalvo.setEmail(cliente.getEmail());

        when(clienteRepository.save(cliente)).thenReturn(clienteSalvo);

        Cliente resultado = clienteService.criarCliente(cliente);

        assertNotNull(cliente);
        assertEquals("Gustavo Fernandes", resultado.getNome());
        assertThat(resultado.getNome()).isEqualTo("Gustavo Fernandes");
        assertThat(resultado.getId()).isEqualTo(1L);
    }
}