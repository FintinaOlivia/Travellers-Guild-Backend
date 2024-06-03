package com.mpp.backend.Controllers;

import com.mpp.backend.DTOs.AuthenticationResponseDTO;
import com.mpp.backend.DTOs.LoginDTO;
import com.mpp.backend.DTOs.RegisterDTO;
import com.mpp.backend.Model.Role;
import com.mpp.backend.Model.UserEntity;
import com.mpp.backend.Repository.RoleRepository;
import com.mpp.backend.Repository.UserRepository;
import com.mpp.backend.Security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    UserRepository userRepository,
                                    RoleRepository roleRepository,
                                    PasswordEncoder passwordEncoder,
                                    JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        System.out.println("Login request received");
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            System.out.println("validadate: " + jwtGenerator.validateToken(token));
            return new ResponseEntity<>(new AuthenticationResponseDTO(token), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AuthenticationResponseDTO("Invalid token!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        System.out.println("Register request received");
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        } else {
            UserEntity newUser = new UserEntity();
            newUser.setUsername(registerDTO.getUsername());
            newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            Role role = roleRepository.findByRoleName("USER").orElse(null);
            newUser.setRoles(Collections.singletonList(role));
            userRepository.save(newUser);
            return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
        }
    }
}
