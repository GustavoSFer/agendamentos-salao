package br.com.fernandes.controller;

import br.com.fernandes.dto.ServicoDTO;
import br.com.fernandes.entities.Servico;
import br.com.fernandes.service.ServicoService;
import br.com.fernandes.util.ServicoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public ResponseEntity<Servico> criarServico(@Valid @RequestBody ServicoDTO servicoDto) {
        Servico servico = ServicoMapper.servicoDtoToServico(servicoDto);

        Servico servicoCriado = servicoService.criarServico(servico);
        URI location = URI.create("/servicos" + servicoCriado.getId());

        return ResponseEntity.created(location).body(servicoCriado);
    }
  
    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos() {
        List<Servico> servicos = servicoService.listarServicos();

        return ResponseEntity.ok().body(servicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscaServicoPeloId(@PathVariable Long id) {
        Servico servico = servicoService.buscarServicoPeloId(id);

        return ResponseEntity.ok().body(servico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Servico> deletarServico(@PathVariable Long id) {
        servicoService.deletarServico(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizarServico(@RequestBody Servico servicoAtual, @PathVariable Long id) {
        Servico servicoAtualizado = servicoService.atualizarServico(servicoAtual, id);

        return ResponseEntity.ok().body(servicoAtualizado);
    }
}
