package br.com.crechesystem.crechesystem.repository;

import br.com.crechesystem.crechesystem.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByLogin(String login);

}