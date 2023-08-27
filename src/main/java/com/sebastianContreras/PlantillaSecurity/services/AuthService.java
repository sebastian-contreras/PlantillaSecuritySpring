package com.sebastianContreras.PlantillaSecurity.services;

import com.sebastianContreras.PlantillaSecurity.controllers.models.AuthResponse;
import com.sebastianContreras.PlantillaSecurity.controllers.models.AuthenticationRequest;
import com.sebastianContreras.PlantillaSecurity.controllers.models.RegisterRequest;
import org.springframework.stereotype.Service;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthenticationRequest request);

}
