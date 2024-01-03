package ru.aiteko.WebStore.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "Запрос для просмотра полного списка товаров")
@Data
public class ProductPageRequestDto {
    @NotNull
    @Min(0)
    private Integer page;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer size;

}
