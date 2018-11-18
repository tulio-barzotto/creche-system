package br.com.crechesystem.crechesystem.repository;

import br.com.crechesystem.crechesystem.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    @Query(
            value = " SELECT * FROM TURMA t WHERE t.minimo_meses <= ?1 AND t.maximo_meses >= ?1 limit 1 ",
            nativeQuery = true)
    Optional<Turma> findByMinAndMaxMonths(int months);
}