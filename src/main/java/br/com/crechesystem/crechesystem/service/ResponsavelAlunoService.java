package br.com.crechesystem.crechesystem.service;

import br.com.crechesystem.crechesystem.domain.ResponsavelAluno;

import java.util.List;
import java.util.Optional;

public interface ResponsavelAlunoService {
    Optional<ResponsavelAluno> findOne(Long id);
    List<ResponsavelAluno> findAll();
}
