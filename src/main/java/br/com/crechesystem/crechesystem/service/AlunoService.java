package br.com.crechesystem.crechesystem.service;

import br.com.crechesystem.crechesystem.domain.Aluno;
import br.com.crechesystem.crechesystem.dto.AlunoDTO;

import java.util.List;
import java.util.Optional;

public interface AlunoService {
    Optional<Aluno> findOne(Long id);
    List<Aluno> findAll();
    Aluno save(AlunoDTO alunoDTO) throws Exception;
    void delete(Long id) throws Exception;
    boolean existsByIdResponsavelAluno(Long idResponsavelAluno);
}
