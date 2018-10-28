package br.com.crechesystem.crechesystem.security;

import br.com.crechesystem.crechesystem.domain.Funcionario;
import br.com.crechesystem.crechesystem.repository.FuncionarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public UserDetailsServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Funcionario> employee = funcionarioRepository.findByLogin(login);
        if (!employee.isPresent()) throw new UsernameNotFoundException("Cannot find user with login: " + login);
        logger.info("load user {}", employee.get().getUsername());
        return new UserDetailsImpl(employee.get());
    }
}
