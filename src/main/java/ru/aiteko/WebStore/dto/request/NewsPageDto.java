package ru.aiteko.WebStore.dto.request;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.aiteko.WebStore.dto.NewsDto;
import ru.aiteko.WebStore.dto.views.NewsView;

import java.util.List;

@Data
@AllArgsConstructor
@JsonView(NewsView.FullInfo.class)
@Schema(description = "Запрос для просмотра списка новостей")
public class NewsPageDto {
    private List<NewsDto> news;
    private int currentPage;
    private int totalPage;
}
