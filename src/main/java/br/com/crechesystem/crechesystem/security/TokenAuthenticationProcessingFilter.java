package br.com.crechesystem.crechesystem.security;

import br.com.crechesystem.crechesystem.domain.Funcionario;
import br.com.crechesystem.crechesystem.dto.LoginDTO;
import br.com.crechesystem.crechesystem.utils.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationProcessingFilter.class);

    private final TokenAuthenticationService tokenAuthenticationService;
    private final ObjectMapper objectMapper;

    public TokenAuthenticationProcessingFilter(String urlMapping,
                                               TokenAuthenticationService tokenAuthenticationService,
                                               AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(urlMapping, "POST", false));
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.objectMapper = new ObjectMapper();
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Attempt to authenticate user");
        String body = HttpUtils.extractPostRequestBody(request);
        final LoginDTO loginDTO = objectMapper.readValue(body, LoginDTO.class);
        final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        return getAuthenticationManager().authenticate(loginToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) {

        LOGGER.info("Authenticate user successfully");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserAuthentication userAuthentication = new UserAuthentication(userDetails);
        userAuthentication.setAuthenticated(true);

        // Add the token to the HTTP response header
        tokenAuthenticationService.addTokenToResponse(userAuthentication, response);

        // Add the userAuthentication to the Security context
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
    }
}
