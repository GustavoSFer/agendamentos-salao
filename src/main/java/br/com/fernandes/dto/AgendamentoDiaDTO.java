package br.com.fernandes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class AgendamentoDiaDTO {
    @JsonProperty("data")
    private AgendamentoData data;

    @Data
    public class AgendamentoData {
        @JsonProperty("cliente")
        private String clienteNome;
        @JsonProperty("telefone")
        String telefone;
        @JsonProperty("email")
        String email;
        @JsonProperty("Servico")
        private String servico;
        @JsonProperty("Data")
        private Date data;
    }
}
