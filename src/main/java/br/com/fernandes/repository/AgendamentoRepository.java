package br.com.fernandes.repository;

import br.com.fernandes.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByClienteId(Long clienteId);
    List<Agendamento> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
}
