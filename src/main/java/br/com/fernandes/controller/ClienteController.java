package br.com.fernandes.controller;

import br.com.fernandes.dto.ClienteDTO;
import br.com.fernandes.entities.Cliente;
import br.com.fernandes.service.ClienteService;
import br.com.fernandes.util.ClienteMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        Cliente clienteCriado = clienteService.criarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
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

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
        Cliente clienteAtualizado = clienteService.atualizaCliente(clienteDTO, id);

        return ResponseEntity.ok().body(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletaCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);

        return ResponseEntity.noContent().build();
    }
}
