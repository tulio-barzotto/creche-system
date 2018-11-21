package br.com.crechesystem.crechesystem.repository;

import br.com.crechesystem.crechesystem.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query(
            value = " SELECT count(1) FROM ALUNO a WHERE a.responsavel_aluno_id = ?1 ",
            nativeQuery = true)
    Long countByIdResponsavelAluno(Long idResponsavelAluno);
}