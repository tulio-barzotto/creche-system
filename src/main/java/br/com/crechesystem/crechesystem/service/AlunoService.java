package br.com.crechesystem.crechesystem.service;

import br.com.crechesystem.crechesystem.domain.Aluno;

import java.util.List;
import java.util.Optional;

public interface AlunoService {
    Optional<Aluno> findOne(Long id);
    List<Aluno> findAll();
}
