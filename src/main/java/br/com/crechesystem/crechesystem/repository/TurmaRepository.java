package br.com.crechesystem.crechesystem.repository;

import br.com.crechesystem.crechesystem.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    @Query(
            value = "SELECT TOP 1 * FROM TURMA t WHERE t.minimumMonths <= ?1 AND t.maximumMonths >= ?1",
            nativeQuery = true)
    Optional<Turma> findByMinAndMaxMonths(int months);
}