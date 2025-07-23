package br.com.fernandes.controller;

import br.com.fernandes.dto.ClienteDTO;
import br.com.fernandes.entities.Cliente;
import br.com.fernandes.service.ClienteService;
import br.com.fernandes.util.ClienteMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.ClienteDtoToCliente(clienteDTO);
        Cliente clienteCriado = clienteService.crairCliente(cliente);
        return ResponseEntity.ok().body(clienteCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscaClienteId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscaClientePeloId(id);

        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listaClientes() {
        List<Cliente> clientes = clienteService.listarClientes();

        return ResponseEntity.ok().body(clientes);
    }

    @PutMapping
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualizaCliente(cliente);

        return ResponseEntity.ok().body(clienteAtualizado);
    }
}
