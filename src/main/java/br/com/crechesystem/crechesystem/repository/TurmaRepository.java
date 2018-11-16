package br.com.crechesystem.crechesystem.repository;

import br.com.crechesystem.crechesystem.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    Optional<Turma> findTop1ByMinimumMonthsLessThanEqualByMaximumMonthsLessThanEqual(long months);
}