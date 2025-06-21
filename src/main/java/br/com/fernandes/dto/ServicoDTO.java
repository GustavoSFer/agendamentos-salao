package br.com.fernandes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ServicoDTO(
        @JsonProperty("nome")
        @NotBlank(message = "O atributo nome deve ser informado.")
        String nome,

        @JsonProperty("descricao")
        String descricao,

        @JsonProperty("preco")
        @Positive(message = "O valor deve ser positivo")
        double preco
) {
}
