package ru.aiteko.WebStore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.aiteko.WebStore.entity.Role;
import ru.aiteko.WebStore.entity.Users;
import ru.aiteko.WebStore.repository.UserRepository;

import java.util.Collections;

@OpenAPIDefinition(
		info = @Info(
				title = "WEB-сервис WebStore",
				description ="OpenAPI сервиса с описанием всех контроллеров и методов",
				version = "1.0.0"
		)
)
@RequiredArgsConstructor
@SpringBootApplication
public class WebStoreApplication {
	public static void main(String[] args) throws Throwable {
		SpringApplication.run(WebStoreApplication.class, args);
	}

	private final UserRepository userRepository;
	@PostConstruct
	public void init() {
		if (!userRepository.existsByUsername("admin")) {
			Users adminUser = new Users();
			adminUser.setUsername("admin");
			adminUser.setPassword("admin");
			adminUser.setEmail("admin@example.com");
			adminUser.setRole(Collections.singleton(Role.ADMIN));
			userRepository.save(adminUser);
		}
	}
}
