package ru.aiteko.WebStore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "news_and_promotions")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "News entity")
public class NewsAndPromotions {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "News ID", example = "1", hidden = true)
    private long id;

    @Column(name = "title", nullable = false, length = 40)
    @Schema(description = "News title", example = "Example title")
    private String title;

    @Column(name = "description", nullable = false, length = 500)
    @Schema(description = "News description", example = "Example description")
    private String description;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "News creation date", example = "2024-03-11 18:50:50", hidden = true)
    private LocalDateTime creationDate;
}
