package br.com.crechesystem.crechesystem.controller;

import br.com.crechesystem.crechesystem.domain.ResponsavelAluno;
import br.com.crechesystem.crechesystem.dto.ResponsavelAlunoDTO;
import br.com.crechesystem.crechesystem.service.ResponsavelAlunoService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/responsaveis-alunos")
public class ResponsavelAlunoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponsavelAlunoController.class);

    private final ResponsavelAlunoService responsavelAlunoService;

    @Autowired
    public ResponsavelAlunoController(ResponsavelAlunoService responsavelAlunoService) {
        this.responsavelAlunoService = responsavelAlunoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getResponsavelAlunoById(@PathVariable Long id) {
        LOGGER.info("Requisição Responsável aluno por ID: {}", id);
        Optional<ResponsavelAluno> responsavelAluno = responsavelAlunoService.findOne(id);
        return responsavelAluno.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<ResponsavelAluno>> getAll() {
        List<ResponsavelAluno> responsaveisAlunos = responsavelAlunoService.findAll();
        if(CollectionUtils.isNotEmpty(responsaveisAlunos)) {
            return new ResponseEntity<>(responsaveisAlunos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<ResponsavelAluno> create(@RequestBody ResponsavelAlunoDTO responsavelAlunoDTO) throws Exception {
        LOGGER.info("Request para criar Responsável aluno");
        ResponsavelAluno responsavelAluno = responsavelAlunoService.save(responsavelAlunoDTO);
        return new ResponseEntity<>(responsavelAluno, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteResponsavelAlunoById(@PathVariable Long id) {
        LOGGER.info("Requisição para deletar Responsavel aluno ID: {}", id);
        try{
            responsavelAlunoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
