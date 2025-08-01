package br.com.fernandes.service;

import br.com.fernandes.entities.Cliente;
import br.com.fernandes.exceptions.ClienteNotFoundException;
import br.com.fernandes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente buscaClientePeloId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente atualizaCliente(Cliente clienteAtualizar, Long id) {
        Cliente clienteAntigo = buscaClientePeloId(id);
        Cliente clienteAtualizado = atualiza(clienteAntigo, clienteAtualizar);

        return clienteRepository.save(clienteAtualizado);
    }

    private Cliente atualiza(Cliente clienteAntigo, Cliente clienteAtualizar) {
        clienteAntigo.setNome(clienteAtualizar.getNome());
        clienteAntigo.setEmail(clienteAtualizar.getEmail());
        clienteAntigo.setTelefone(clienteAtualizar.getTelefone());

        return clienteAntigo;
    }

    public void deleteCliente(Long id) {
        Cliente cliente = buscaClientePeloId(id);

        clienteRepository.delete(cliente);
    }
}
