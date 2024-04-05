package ru.aiteko.WebStore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "LogIn User Dto")
public class LoginDto {
    @JsonProperty("usernameOrEmail")
    @Schema(description = "User username or user email", example = "username123 or email123@example.com")
    private String usernameOrEmail;

    @JsonProperty("password")
    @Schema(description = "User password", example = "123456789")
    private String password;
}
