package br.com.fernandes.util;

import br.com.fernandes.dto.ServicoDTO;
import br.com.fernandes.entities.Servico;

public class ServicoMapper {

    public static Servico servicoDtoToServico(ServicoDTO servicoDTO) {
        Servico servico = new Servico();
        servico.setNome(servicoDTO.nome());
        servico.setDescricao(servicoDTO.descricao());
        servico.setPreco(servicoDTO.preco());

        return servico;
    }
}
