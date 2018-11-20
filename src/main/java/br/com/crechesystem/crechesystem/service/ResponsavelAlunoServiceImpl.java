package br.com.crechesystem.crechesystem.service;

import br.com.crechesystem.crechesystem.domain.Responsavel;
import br.com.crechesystem.crechesystem.domain.ResponsavelAluno;
import br.com.crechesystem.crechesystem.dto.ResponsavelAlunoDTO;
import br.com.crechesystem.crechesystem.dto.ResponsavelDTO;
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

    private final ResponsavelService responsavelService;

    @Autowired
    public ResponsavelAlunoServiceImpl(ResponsavelAlunoRepository responsavelAlunoRepository, ResponsavelService responsavelService) {
        this.responsavelAlunoRepository = responsavelAlunoRepository;
        this.responsavelService = responsavelService;
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

    @Override
    public ResponsavelAluno save(ResponsavelAlunoDTO responsavelAlunoDTO) throws Exception {
        LOGGER.info("Salvando Responsavel aluno: {}", responsavelAlunoDTO);
        //TODO: validações
        Responsavel responsavelMae = responsavelService.save(this.buildResponsavel(responsavelAlunoDTO.getResponsavelMae()));
        Responsavel responsavelPai = responsavelService.save(this.buildResponsavel(responsavelAlunoDTO.getResponsavelPai()));
        ResponsavelAluno responsavelAluno = new ResponsavelAluno();
        responsavelAluno.setResponsavelMae(responsavelMae);
        responsavelAluno.setResponsavelPai(responsavelPai);
        return this.responsavelAlunoRepository.save(responsavelAluno);
    }

    @Override
    public void delete(Long id) throws Exception {
        LOGGER.info("Deletando Responsavel aluno ID: {}", id);
        Optional<ResponsavelAluno> responsavelAluno = findOne(id);
        if(responsavelAluno.isPresent()) {
            responsavelAlunoRepository.delete(responsavelAluno.get());
        } else {
            throw new Exception("Responsavel Aluno não encontrado");
        }
    }

    private Responsavel buildResponsavel(ResponsavelDTO responsavelDTO) {
        if(responsavelDTO != null) {
            Responsavel responsavel = new Responsavel();
            responsavel.setName(responsavelDTO.getName());
            responsavel.setAddress(responsavelDTO.getAddress());
            responsavel.setCity(responsavelDTO.getCity());
            responsavel.setUf(responsavelDTO.getUf());
            responsavel.setPhoneDdd(responsavelDTO.getDddPhoneNumber());
            responsavel.setPhoneNumber(responsavelDTO.getPhoneNumber());
            return responsavel;
        } else {
            return null;
        }
    }
}
