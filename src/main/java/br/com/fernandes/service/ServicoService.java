package br.com.fernandes.service;

import br.com.fernandes.entities.Servico;
import br.com.fernandes.exceptions.ServicoNotFoundException;
import br.com.fernandes.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
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

    public List<Servico> listarServicos() {
        List<Servico> servicos = servicoRepository.findAll();
       List<Servico> servicosOrdenados = servicos.stream().sorted(Comparator.comparing(Servico::getNome)).toList();
        return servicosOrdenados;
    }
}
