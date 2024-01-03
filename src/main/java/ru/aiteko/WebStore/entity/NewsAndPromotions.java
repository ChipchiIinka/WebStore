package ru.aiteko.WebStore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.aiteko.WebStore.entity.views.NewsView;
import java.time.LocalDateTime;

@Entity
@Table(name = "news_and_promotions")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "News entity")
public class NewsAndPromotions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(hidden = true)
    private long id;

    @Column(name = "title", nullable = false, length = 40)
    @JsonView(NewsView.ShortInfo.class)
    @Schema(description = "News title", example = "Example title")
    private String title;

    @Column(name = "description", nullable = false, length = 500)
    @JsonView(NewsView.FullInfo.class)
    @Schema(description = "News description", example = "Example description")
    private String description;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @JsonView(NewsView.ShortInfo.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(hidden = true)
    private LocalDateTime creation_date;
}
