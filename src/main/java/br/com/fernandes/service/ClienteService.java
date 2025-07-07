package br.com.fernandes.service;

import br.com.fernandes.entities.Cliente;
import br.com.fernandes.exceptions.ClienteNotFoundException;
import br.com.fernandes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente crairCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente buscaClientePeloId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));
    }
}
