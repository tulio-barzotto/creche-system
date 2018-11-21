package br.com.crechesystem.crechesystem.service;

import br.com.crechesystem.crechesystem.domain.Responsavel;
import br.com.crechesystem.crechesystem.domain.ResponsavelAluno;
import br.com.crechesystem.crechesystem.dto.ResponsavelAlunoDTO;
import br.com.crechesystem.crechesystem.dto.ResponsavelDTO;
import br.com.crechesystem.crechesystem.exceptions.BusinessRuleException;
import br.com.crechesystem.crechesystem.repository.ResponsavelAlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelAlunoServiceImpl implements ResponsavelAlunoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponsavelAlunoServiceImpl.class);

    private final ResponsavelAlunoRepository responsavelAlunoRepository;

    private final ResponsavelService responsavelService;

    private final AlunoService alunoService;

    @Autowired
    public ResponsavelAlunoServiceImpl(ResponsavelAlunoRepository responsavelAlunoRepository, ResponsavelService responsavelService, @Lazy AlunoService alunoService) {
        this.responsavelAlunoRepository = responsavelAlunoRepository;
        this.responsavelService = responsavelService;
        this.alunoService = alunoService;
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

    @Transactional
    @Override
    public ResponsavelAluno save(ResponsavelAlunoDTO responsavelAlunoDTO) throws Exception {
        LOGGER.info("Salvando Responsavel aluno: {}", responsavelAlunoDTO);
        //TODO Validar dados
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
            if(alunoService.existsByIdResponsavelAluno(id)){
                throw new BusinessRuleException("Responsavel já possui aluno vinculado");
            } else {
                responsavelAlunoRepository.delete(responsavelAluno.get());
            }
        } else {
            throw new BusinessRuleException("Responsavel Aluno não encontrado");
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
