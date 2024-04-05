package ru.aiteko.WebStore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import ru.aiteko.WebStore.dto.views.NewsView;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Info about news and promotions")
public class NewsDto {
    @JsonProperty("id")
    @JsonView(NewsView.FullInfo.class)
    @Schema(description = "News ID", example = "1", hidden = true)
    private long id;

    @JsonProperty("title")
    @JsonView(NewsView.ShortInfo.class)
    @Schema(description = "News title", example = "Example title")
    private String title;

    @JsonProperty("description")
    @JsonView(NewsView.ShortInfo.class)
    @Schema(description = "News description", example = "Example description")
    private String description;

    @JsonProperty("creation_date")
    @JsonView(NewsView.FullInfo.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "News creation date", example = "2024-03-11 18:50:50", hidden = true)
    private LocalDateTime creationDate;
}
