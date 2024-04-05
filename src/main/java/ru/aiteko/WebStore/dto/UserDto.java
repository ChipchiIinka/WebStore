package ru.aiteko.WebStore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.aiteko.WebStore.entity.Products;
import ru.aiteko.WebStore.entity.Role;
import ru.aiteko.WebStore.dto.views.UserView;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Schema(description = "Info about user")
public class UserDto {

    @JsonProperty("id")
    @Schema(hidden = true)
    private long id;

    @JsonProperty("username")
    @JsonView(UserView.ShortInfo.class)
    @Schema(description = "User username", example = "username123")
    private String username;

    @JsonProperty("password")
    @Schema(description = "User password", example = "123456789")
    private String password;

    @JsonProperty("email")
    @JsonView(UserView.ShortInfo.class)
    @Schema(description = "User email", example = "email@example.com")
    private String email;

    @JsonProperty("role")
    @Schema(description = "User role", example = "ADMIN", hidden = true)
    private Set<Role> role;

    @JsonProperty("shopping_cart")
    @Schema(description = "User shopping cart", example = "1", hidden = true)
    private Set<Products> shoppingCarts;
}
