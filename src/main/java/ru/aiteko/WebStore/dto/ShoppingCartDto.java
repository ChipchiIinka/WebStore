package ru.aiteko.WebStore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import ru.aiteko.WebStore.entity.Products;
import ru.aiteko.WebStore.entity.Users;

import java.util.Set;

@Data
@Builder
@Schema(description = "Info about shopping cart")
public class ShoppingCartDto {

    @JsonProperty("id")
    @Schema(description = "Shopping cart id", example = "1")
    private long id;

    @JsonProperty("products")
    @Schema(description = "Shopping cart products", example = "id = 1, title = product")
    private Set<Products> products;

    @JsonProperty("user")
    @Schema(description = "Shopping cart user", example = "id = 1, username = username")
    private Users user;
}
