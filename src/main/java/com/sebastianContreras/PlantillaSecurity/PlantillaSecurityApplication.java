package com.sebastianContreras.PlantillaSecurity;

import com.sebastianContreras.PlantillaSecurity.entities.Eroles;
import com.sebastianContreras.PlantillaSecurity.entities.UserEntity;
import com.sebastianContreras.PlantillaSecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PlantillaSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantillaSecurityApplication.class, args);
	}


	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Bean
	CommandLineRunner init(){
		return args -> {

			UserEntity userEntity = UserEntity.builder()
					.email("santiago@mail.com")
					.firstname("santiago")
					.lastname("avila")
					.password(passwordEncoder.encode("1234"))
					.userRole(Eroles.ADMIN)
							.build();

			UserEntity userEntity2 = UserEntity.builder()
					.email("sebas@mail.com")
					.firstname("sebastian")
					.lastname("contreras")
					.password(passwordEncoder.encode("1234"))
					.userRole(Eroles.USER)
					.build();


			userRepository.save(userEntity);
			userRepository.save(userEntity2);
		};
	}

}
