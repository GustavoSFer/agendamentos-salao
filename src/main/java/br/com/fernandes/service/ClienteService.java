package br.com.fernandes.service;

import br.com.fernandes.dto.ClienteDTO;
import br.com.fernandes.entities.Cliente;
import br.com.fernandes.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente crairCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
