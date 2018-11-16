package br.com.crechesystem.crechesystem.service;

import br.com.crechesystem.crechesystem.domain.Aluno;
import br.com.crechesystem.crechesystem.domain.ResponsavelAluno;
import br.com.crechesystem.crechesystem.domain.Turma;
import br.com.crechesystem.crechesystem.dto.AlunoDTO;
import br.com.crechesystem.crechesystem.exceptions.BusinessRuleException;
import br.com.crechesystem.crechesystem.repository.AlunoRepository;
import br.com.crechesystem.crechesystem.utils.DateUtils;
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

    private final TurmaService turmaService;

    @Autowired
    public AlunoServiceImpl(AlunoRepository alunoRepository, ResponsavelAlunoService responsavelAlunoService, TurmaService turmaService) {
        this.alunoRepository = alunoRepository;
        this.responsavelAlunoService = responsavelAlunoService;
        this.turmaService = turmaService;
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
        this.isValid(alunoDTO);
        Optional<ResponsavelAluno> optResponsavelAluno = responsavelAlunoService.findOne(alunoDTO.getIdResponsavelAluno());
        Optional<Turma> optTurma = turmaService.findByBirthMonth(DateUtils.getMonthFromBirthdate(alunoDTO.getBirthdate()));
        if(optResponsavelAluno.isPresent() && optTurma.isPresent()) {
            Aluno aluno = new Aluno();
            aluno.setName(alunoDTO.getName());
            aluno.setBirthdate(alunoDTO.getBirthdate());
            aluno.setResponsavelAluno(optResponsavelAluno.get());
            aluno.setTurma(optTurma.get());
            return alunoRepository.save(aluno);
        } else {
            throw new BusinessRuleException("Erro ao salvar Aluno");
        }
    }

    private void isValid(AlunoDTO alunoDTO) throws BusinessRuleException {
        Optional<ResponsavelAluno> optResponsavelAluno = responsavelAlunoService.findOne(alunoDTO.getIdResponsavelAluno());
        if(!optResponsavelAluno.isPresent()) {
            throw new BusinessRuleException("Responsável inválido");
        }
        Optional<Turma> optTurma = turmaService.findByBirthMonth(DateUtils.getMonthFromBirthdate(alunoDTO.getBirthdate()));
        if(!optTurma.isPresent()) {
            throw new BusinessRuleException("Turma não encontrada");
        } else {
            Turma turma = optTurma.get();
            if(turma.getAlunosTotal() + 1 >= turma.getMaximumCapacity()) {
                throw new BusinessRuleException("Turma " + turma.getName() + " atingiu a capacidade máxima de alunos");
            }
        }
    }
}
