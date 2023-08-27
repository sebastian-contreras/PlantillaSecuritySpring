package com.sebastianContreras.PlantillaSecurity.services;

import com.sebastianContreras.PlantillaSecurity.config.JwtService;
import com.sebastianContreras.PlantillaSecurity.controllers.models.AuthResponse;
import com.sebastianContreras.PlantillaSecurity.controllers.models.AuthenticationRequest;
import com.sebastianContreras.PlantillaSecurity.controllers.models.RegisterRequest;
import com.sebastianContreras.PlantillaSecurity.entities.Eroles;
import com.sebastianContreras.PlantillaSecurity.entities.UserEntity;
import com.sebastianContreras.PlantillaSecurity.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse register(RegisterRequest request) {
        var user = UserEntity.builder()
                .email(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(Eroles.USER)
                .build();
        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token).build();
    }

    @Override
    public AuthResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        System.out.println(request.getPassword());
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }
}
