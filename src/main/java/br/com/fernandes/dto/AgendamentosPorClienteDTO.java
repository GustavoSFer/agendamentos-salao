package br.com.fernandes.dto;

import java.time.LocalDateTime;
import java.util.List;

public class AgendamentosPorClienteDTO {

    private ClienteDTO cliente;
    private List<AgendamentoSimplesDTO> agendamentos;

    public AgendamentosPorClienteDTO(ClienteDTO cliente, List<AgendamentoSimplesDTO> agendamentos) {
        this.cliente = cliente;
        this.agendamentos = agendamentos;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public List<AgendamentoSimplesDTO> getAgendamentos() {
        return agendamentos;
    }

    public static class ClienteDTO {
        private String nome;
        private String telefone;
        private String email;

        public ClienteDTO(String nome, String telefone, String email) {
            this.nome = nome;
            this.telefone = telefone;
            this.email = email;
        }

        public String getNome() {
            return nome;
        }

        public String getTelefone() {
            return telefone;
        }

        public String getEmail() {
            return email;
        }
    }

    public static class ServicoDTO {
        private String nome;
        private String descricao;
        private double preco;

        public ServicoDTO(String nome, String descricao, double preco) {
            this.nome = nome;
            this.descricao = descricao;
            this.preco = preco;
        }

        public String getNome() {
            return nome;
        }

        public String getDescricao() {
            return descricao;
        }

        public double getPreco() {
            return preco;
        }
    }

    public static class AgendamentoSimplesDTO {
        private ServicoDTO servico;
        private LocalDateTime dataHora;
        private String observacao;

        public AgendamentoSimplesDTO(ServicoDTO servico, LocalDateTime dataHora, String observacao) {
            this.servico = servico;
            this.dataHora = dataHora;
            this.observacao = observacao;
        }

        public ServicoDTO getServico() {
            return servico;
        }

        public LocalDateTime getDataHora() {
            return dataHora;
        }

        public String getObservacao() {
            return observacao;
        }
    }
}
