package br.com.crechesystem.crechesystem.service;

import br.com.crechesystem.crechesystem.domain.Turma;
import br.com.crechesystem.crechesystem.repository.TurmaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TurmaServiceImpl implements TurmaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurmaServiceImpl.class);

    private TurmaRepository turmaRepository;

    @Autowired
    public TurmaServiceImpl(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    @Override
    public Optional<Turma> findOne(Long id) {
        LOGGER.info("Pesquisando Turma pelo ID: {}", id);
        return this.turmaRepository.findById(id);
    }

    @Override
    public List<Turma> findAll() {
        LOGGER.info("Pesquisando todas as Turmas");
        return this.turmaRepository.findAll();
    }

    @Override
    public Optional<Turma> findByBirthMonth(long months) {
        return this.turmaRepository.findTop1ByMinimumMonthsLessThanEqualByMaximumMonthsLessThanEqual(months);
    }
}
