package ru.aiteko.WebStore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	public static void main(String[] args){
		SpringApplication.run(WebStoreApplication.class, args);
	}
}
