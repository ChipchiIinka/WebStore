package ru.aiteko.WebStore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import ru.aiteko.WebStore.entity.views.ProductView;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Product entity")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(ProductView.FullInfo.class)
    @Column(name = "id", nullable = false)
    @Schema(hidden = true)
    private long id;

    @Column(name = "title", nullable = false, unique = true, length = 40)
    @JsonView(ProductView.ShortInfo.class)
    @Schema(description = "Product title", example = "Example title")
    private String title;

    @Column(name = "description", nullable = false, length = 400)
    @JsonView(ProductView.AverageInfo.class)
    @Schema(description = "Product description", example = "Example description")
    private String description;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @JsonView(ProductView.FullInfo.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(hidden = true)
    private LocalDateTime creation_date;

    @Column(name = "price", nullable = false)
    @JsonView(ProductView.ShortInfo.class)
    @Schema(description = "Product price", example = "19.99")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(ProductView.FullInfo.class)
    @Schema(hidden = true)
    private Users user;
}
