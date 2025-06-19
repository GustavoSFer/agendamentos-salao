package br.com.fernandes.controller;

import br.com.fernandes.dto.ClienteDTO;
import br.com.fernandes.entities.Cliente;
import br.com.fernandes.service.ClienteService;
import br.com.fernandes.util.ClienteMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.ClienteDtoToCliente(clienteDTO);
        Cliente clienteCriado = clienteService.crairCliente(cliente);
        return ResponseEntity.ok().body(clienteCriado);
    }
}
