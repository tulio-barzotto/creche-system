package br.com.crechesystem.crechesystem.service;

import br.com.crechesystem.crechesystem.domain.Responsavel;

import java.util.List;
import java.util.Optional;

public interface ResponsavelService {
    Optional<Responsavel> findOne(Long id);
    List<Responsavel> findAll();
}
