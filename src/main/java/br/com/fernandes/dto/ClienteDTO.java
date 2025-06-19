package br.com.fernandes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record ClienteDTO(
        @JsonProperty("nome")
        @NotBlank(message = "O nome deve ser informado.")
        String nome,
        @JsonProperty("telefone")
        @NotBlank(message = "O telefone deve ser informado.")
        String telefone,
        @JsonProperty("email")
        @NotBlank(message = "O email deve ser informado.")
        String email
) {
}
