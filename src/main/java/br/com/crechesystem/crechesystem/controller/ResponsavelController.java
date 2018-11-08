package br.com.crechesystem.crechesystem.controller;

import br.com.crechesystem.crechesystem.domain.Responsavel;
import br.com.crechesystem.crechesystem.service.ResponsavelService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/responsaveis")
public class ResponsavelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponsavelController.class);

    private final ResponsavelService responsavelService;

    @Autowired
    public ResponsavelController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getResponsavelById(@PathVariable Long id) {
        LOGGER.info("Requisição Turma por ID: {}", id);
        Optional<Responsavel> responsavel = responsavelService.findOne(id);
        return responsavel.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Responsavel>> getAll() {
        List<Responsavel> responsaveis = responsavelService.findAll();
        if(CollectionUtils.isNotEmpty(responsaveis)) {
            return new ResponseEntity<>(responsaveis, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        }
    }
}
