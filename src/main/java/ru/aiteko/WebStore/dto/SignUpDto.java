package ru.aiteko.WebStore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import ru.aiteko.WebStore.dto.views.UserView;

@Data
@Builder
@Schema(description = "SignUp User Dto")
public class SignUpDto {
    @JsonProperty("username")
    @JsonView(UserView.ShortInfo.class)
    @Schema(description = "User username", example = "username123")
    private String username;

    @JsonProperty("password")
    @Schema(description = "User password", example = "123456789")
    private String password;

    @JsonProperty("confirmPassword")
    @Schema(description = "confirmPassword = password", example = "123456789")
    private String confirmPassword;

    @JsonProperty("email")
    @JsonView(UserView.ShortInfo.class)
    @Schema(description = "User email", example = "email@example.com")
    private String email;
}
