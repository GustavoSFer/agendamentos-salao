package br.com.fernandes.util;

import br.com.fernandes.dto.ClienteDTO;
import br.com.fernandes.entities.Cliente;

public class ClienteMapper {

    public static Cliente ClienteDtoToCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.nome());
        cliente.setEmail(clienteDTO.email());
        cliente.setTelefone(clienteDTO.telefone());
        return cliente;
    }
}
