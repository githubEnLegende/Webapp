package org.oxyl.webapp.controller.rest;

import org.oxyl.bindings.dto.logindto.UserLoginDTO;
import org.oxyl.bindings.dto.logindto.UserDataDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class LoginRestController {

    private final AuthenticationManager authenticationManager;

    public LoginRestController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<UserDataDTO> login(@RequestBody UserLoginDTO user) {
        try {
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.username(), user.password())
            );
            var userRole = (User) authentication.getPrincipal();
            return ResponseEntity.ok().body(
                    new UserDataDTO(
                            userRole.getUsername(),
                            userRole.getAuthorities().stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .toList())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
