package br.com.fernandes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AgendamentoDiaDTO {
    @JsonProperty("data")
    private List<AgendamentoData> data;

    @Data
    public static class AgendamentoData {
        @JsonProperty("cliente")
        private String clienteNome;
        @JsonProperty("telefone")
        private String telefone;
        @JsonProperty("email")
        private String email;
        @JsonProperty("Servico")
        private String servico;
        @JsonProperty("Preco")
        private double preco;
        @JsonProperty("Data")
        private Date data;
    }
}
