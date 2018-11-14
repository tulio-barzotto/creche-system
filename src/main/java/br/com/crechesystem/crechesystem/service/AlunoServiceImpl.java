package br.com.crechesystem.crechesystem.service;

import br.com.crechesystem.crechesystem.domain.Aluno;
import br.com.crechesystem.crechesystem.repository.AlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements AlunoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlunoServiceImpl.class);

    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoServiceImpl(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Override
    public Optional<Aluno> findOne(Long id) {
        LOGGER.info("Pesquisando Aluno pelo ID: {}", id);
        return this.alunoRepository.findById(id);
    }

    @Override
    public List<Aluno> findAll() {
        return this.alunoRepository.findAll();
    }
}
