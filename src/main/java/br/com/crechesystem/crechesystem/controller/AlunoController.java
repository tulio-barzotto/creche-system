package br.com.crechesystem.crechesystem.controller;

import br.com.crechesystem.crechesystem.domain.Aluno;
import br.com.crechesystem.crechesystem.dto.AlunoDTO;
import br.com.crechesystem.crechesystem.service.AlunoService;
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
@RequestMapping("/api/alunos")
public class AlunoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlunoController.class);

    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getAlunoById(@PathVariable Long id) {
        LOGGER.info("Requisição Aluno por ID: {}", id);
        Optional<Aluno> aluno = alunoService.findOne(id);
        return aluno.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll() {
        List<Aluno> alunos = alunoService.findAll();
        if(CollectionUtils.isNotEmpty(alunos)) {
            return new ResponseEntity<>(alunos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Aluno> create(AlunoDTO alunoDTO) throws Exception {
        LOGGER.info("Request para criar Aluno");
        Aluno aluno = alunoService.save(alunoDTO);
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }
}
