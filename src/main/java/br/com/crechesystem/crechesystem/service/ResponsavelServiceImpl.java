package br.com.crechesystem.crechesystem.service;

import br.com.crechesystem.crechesystem.domain.Responsavel;
import br.com.crechesystem.crechesystem.repository.ResponsavelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelServiceImpl implements ResponsavelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponsavelServiceImpl.class);

    private final ResponsavelRepository responsavelRepository;

    @Autowired
    public ResponsavelServiceImpl(ResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    @Override
    public Optional<Responsavel> findOne(Long id) {
        LOGGER.info("Pesquisando Responsavel pelo ID: {}", id);
        return this.responsavelRepository.findById(id);
    }

    @Override
    public List<Responsavel> findAll() {
        LOGGER.info("Pesquisando todas os Responsáveis");
        return this.responsavelRepository.findAll();
    }

    @Override
    public Responsavel save(Responsavel responsavel) {
        LOGGER.info("Salvando Responsavel: {}", responsavel);
        return this.responsavelRepository.save(responsavel);
    }
}
