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

    public static class AgendamentoSimplesDTO {
        private String nome;
        private String descricao;
        private double preco;
        private LocalDateTime dataHora;
        private String observacao;

        public AgendamentoSimplesDTO(String nome, String descricao, double preco, LocalDateTime dataHora, String observacao) {
            this.nome = nome;
            this.descricao = descricao;
            this.preco = preco;
            this.dataHora = dataHora;
            this.observacao = observacao;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public double getPreco() {
            return preco;
        }

        public void setPreco(double preco) {
            this.preco = preco;
        }

        public LocalDateTime getDataHora() {
            return dataHora;
        }

        public void setDataHora(LocalDateTime dataHora) {
            this.dataHora = dataHora;
        }

        public String getObservacao() {
            return observacao;
        }

        public void setObservacao(String observacao) {
            this.observacao = observacao;
        }
    }
}
