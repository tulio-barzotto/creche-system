package br.com.crechesystem.crechesystem.repository;

import br.com.crechesystem.crechesystem.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}