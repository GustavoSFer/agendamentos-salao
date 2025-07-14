package br.com.fernandes.controller;

import br.com.fernandes.dto.ServicoDTO;
import br.com.fernandes.entities.Servico;
import br.com.fernandes.service.ServicoService;
import br.com.fernandes.util.ServicoMapper;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscaServicoPeloId(@PathVariable Long id) {
        Servico servico = servicoService.buscarServicoPeloId(id);

        return ResponseEntity.ok().body(servico);
    }
}
