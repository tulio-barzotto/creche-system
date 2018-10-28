package br.com.crechesystem.crechesystem.controller;

import br.com.crechesystem.crechesystem.domain.Turma;
import br.com.crechesystem.crechesystem.service.TurmaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurmaController.class);

    private final TurmaService turmaService;

    @Autowired
    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getTurmaById(@PathVariable Long id) {
        String pw_hash = BCrypt.hashpw("senha123", BCrypt.gensalt());
        LOGGER.info("Requisição Turma por ID: {}", id);
        Optional<Turma> turma = turmaService.findOne(id);
        return turma.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
