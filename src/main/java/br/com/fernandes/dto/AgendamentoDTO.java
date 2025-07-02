package br.com.fernandes.dto;

import br.com.fernandes.entities.Cliente;
import br.com.fernandes.entities.Servico;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoDTO(
        @JsonProperty("cliente")
        @NotNull(message = "O cliente deve ser informado.")
        Cliente cliente,

        @JsonProperty("servico")
        @NotNull(message = "O servico deve ser informado.")
        Servico servico,

        @JsonProperty("dataHora")
        @NotBlank(message = "A data e hora deve ser informado.")
        LocalDateTime dataHora,

        @JsonProperty("observacao")
        String observacao
) {
}
