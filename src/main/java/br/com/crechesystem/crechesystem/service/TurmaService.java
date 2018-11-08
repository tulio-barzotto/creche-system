package br.com.crechesystem.crechesystem.service;

import br.com.crechesystem.crechesystem.domain.Turma;

import java.util.List;
import java.util.Optional;

public interface TurmaService {
    Optional<Turma> findOne(Long id);
    List<Turma> findAll();
}
