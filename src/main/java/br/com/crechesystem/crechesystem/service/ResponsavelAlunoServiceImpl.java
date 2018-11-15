package br.com.crechesystem.crechesystem.service;

import br.com.crechesystem.crechesystem.domain.ResponsavelAluno;
import br.com.crechesystem.crechesystem.repository.ResponsavelAlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelAlunoServiceImpl implements ResponsavelAlunoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponsavelAlunoServiceImpl.class);

    private final ResponsavelAlunoRepository responsavelAlunoRepository;

    @Autowired
    public ResponsavelAlunoServiceImpl(ResponsavelAlunoRepository responsavelAlunoRepository) {
        this.responsavelAlunoRepository = responsavelAlunoRepository;
    }

    @Override
    public Optional<ResponsavelAluno> findOne(Long id) {
        LOGGER.info("Pesquisando ResponsavelAluno pelo ID: {}", id);
        return this.responsavelAlunoRepository.findById(id);
    }

    @Override
    public List<ResponsavelAluno> findAll() {
        LOGGER.info("Pesquisando todas os Responsáveis Aluno");
        return this.responsavelAlunoRepository.findAll();
    }
}
