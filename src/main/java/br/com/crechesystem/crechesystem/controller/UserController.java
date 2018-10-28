package br.com.crechesystem.crechesystem.controller;

import br.com.crechesystem.crechesystem.security.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController() {
    }

    @GetMapping(value = "/current")
    public ResponseEntity getCurrentUser(@CurrentUser Authentication authentication) {
        logger.info("Get current user info");
        return ResponseEntity.ok(authentication.getDetails());
    }
}
