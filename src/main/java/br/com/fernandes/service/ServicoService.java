package br.com.fernandes.service;

import br.com.fernandes.entities.Servico;
import br.com.fernandes.exceptions.ServicoNotFoundException;
import br.com.fernandes.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public Servico criarServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public Servico buscarServicoPeloId(Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);

        return servico.orElseThrow(() -> new ServicoNotFoundException("Serviço informado não encontrado."));
    }

    public Servico deletarServico(Long id) {
        Servico servico = buscarServicoPeloId(id);

        servicoRepository.delete(servico);
        return servico;
    }
}
