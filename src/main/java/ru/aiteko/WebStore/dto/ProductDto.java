package ru.aiteko.WebStore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import ru.aiteko.WebStore.entity.Users;
import ru.aiteko.WebStore.dto.views.ProductView;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Info about Product")
public class ProductDto {
    @JsonProperty("id")
    @JsonView(ProductView.FullInfo.class)
    @Schema(description = "News ID", example = "1", hidden = true)
    private long id;

    @JsonProperty("title")
    @JsonView(ProductView.ShortInfo.class)
    @Schema(description = "Product title", example = "Example title")
    private String title;

    @JsonProperty("description")
    @JsonView(ProductView.AverageInfo.class)
    @Schema(description = "Product description", example = "Example description")
    private String description;

    @JsonProperty("creation_date")
    @JsonView(ProductView.FullInfo.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Product creation date", example = "2024-03-11 18:50:50", hidden = true)
    private LocalDateTime creation_date;

    @JsonProperty("price")
    @JsonView(ProductView.ShortInfo.class)
    @Schema(description = "Product price", example = "19.99")
    private BigDecimal price;

    @JsonProperty("user")
    @JsonView(ProductView.FullInfo.class)
    @Schema(description = "Product owner", example = "id = 1, username = admin", hidden = true)
    private Users user;
}

