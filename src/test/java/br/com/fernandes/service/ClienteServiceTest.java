package br.com.fernandes.service;

import br.com.fernandes.dto.ClienteDTO;
import br.com.fernandes.entities.Cliente;
import br.com.fernandes.exceptions.ClienteNotFoundException;
import br.com.fernandes.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    @DisplayName("Deve ser possivel buscar cliente pelo seu id")
    void testBuscaClientePeloId() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Nome do cliente");
        cliente.setTelefone("11963692525");
        cliente.setEmail("cliente@gmail.com");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente clienteResult = clienteService.buscaClientePeloId(1L);

        assertThat(clienteResult.getNome()).isEqualTo("Nome do cliente");
        assertThat(clienteResult.getId()).isEqualTo(1L);
        assertThat(clienteResult.getEmail()).isEqualTo("cliente@gmail.com");
    }

    @Test
    @DisplayName("Deve retornar um exception ClienteNotFoundException quando não existir o cliente")
    void testExceptionClienteNotFoundException() {
        when(clienteRepository.findById(2L)).thenReturn(Optional.empty());

        Exception error = assertThrows(ClienteNotFoundException.class, () ->
           clienteService.buscaClientePeloId(2L)
        );

        assertEquals("Cliente não encontrado", error.getMessage());
        verify(clienteRepository, times(1)).findById(2L);
    }

    @Test
    @DisplayName("Deve retornar uma lista de clientes")
    void testRetornaListaClientes() {
        List<Cliente> clientes = Arrays.asList(
                new Cliente("nome", "11852588888", "nome@gmail.com"),
                new Cliente("pessoa 2", "11458785555", "pessoa2@gmail.com")
        );

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> resultadoClientes = clienteService.listarClientes();

        assertEquals(2, resultadoClientes.size());
        assertEquals("nome", resultadoClientes.get(0).getNome());
    }

    @Test
    @DisplayName("Deve retornar uma array vazio quando nao existir uma lista de clientes")
    void testRetornaListaVaziaDeClientes() {
        List<Cliente> clientes = new ArrayList<>();

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> resultadoClientes = clienteService.listarClientes();

        assertEquals(0, resultadoClientes.size());
    }

    @Test
    @DisplayName("Atualizando um cliente")
    void testAtualizarCliente() {
        ClienteDTO clienteDto = new ClienteDTO("Cliente Atualizado", "11236563333", "cliente@gmail.com");

        Cliente cliente = new Cliente();
        cliente.setEmail("cliente@gmail.com");
        cliente.setNome("Cliente antigo");
        cliente.setTelefone("11236563333");
        cliente.setId(1L);

        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setEmail(clienteDto.email());
        clienteAtualizado.setNome(clienteDto.nome());
        clienteAtualizado.setTelefone(clienteDto.telefone());
        clienteAtualizado.setId(1L);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteAtualizado);

        Cliente resultado = clienteService.atualizaCliente(clienteDto, 1L);

        assertEquals(resultado.getNome(), clienteAtualizado.getNome());
        assertEquals(resultado.getId(), 1L);
        assertEquals(resultado.getEmail(), clienteAtualizado.getEmail());
    }

    @Test
    @DisplayName("Deve ser possivel deletar um cliente")
    void testDeletandoCliente() {
        Long id = 1L;

        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome("João");
        cliente.setEmail("joao@email.com");

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        clienteService.deleteCliente(id);

        verify(clienteRepository, times(1)).findById(id);
        verify(clienteRepository, times(1)).delete(cliente);
    }
}