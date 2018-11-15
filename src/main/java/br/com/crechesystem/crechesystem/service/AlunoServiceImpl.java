package br.com.crechesystem.crechesystem.service;

import br.com.crechesystem.crechesystem.domain.Aluno;
import br.com.crechesystem.crechesystem.domain.ResponsavelAluno;
import br.com.crechesystem.crechesystem.dto.AlunoDTO;
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

    private final ResponsavelAlunoService responsavelAlunoService;

    @Autowired
    public AlunoServiceImpl(AlunoRepository alunoRepository, ResponsavelAlunoService responsavelAlunoService) {
        this.alunoRepository = alunoRepository;
        this.responsavelAlunoService = responsavelAlunoService;
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

    @Override
    public Aluno save(AlunoDTO alunoDTO) throws Exception {
        LOGGER.info("Salvando usuário: {}", alunoDTO);
        Optional<ResponsavelAluno> optResponsavelAluno = responsavelAlunoService.findOne(alunoDTO.getIdResponsavelAluno());
        if(!optResponsavelAluno.isPresent()) {
            throw new Exception("Responsável inválido");
        } else {
            //TODO: verificar qual turma se encaixa
            Aluno aluno = new Aluno();
            aluno.setName(alunoDTO.getName());
            aluno.setBirthdate(alunoDTO.getBirthdate());
            aluno.setResponsavelAluno(optResponsavelAluno.get());
            return alunoRepository.save(aluno);
        }
    }
}
