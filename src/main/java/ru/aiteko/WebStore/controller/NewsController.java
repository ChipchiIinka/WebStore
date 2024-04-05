package ru.aiteko.WebStore.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.aiteko.WebStore.dto.NewsDto;
import ru.aiteko.WebStore.dto.views.NewsView;
import ru.aiteko.WebStore.service.NewsService;
import java.util.List;

@Validated
@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Tag(name = "Сервис для работы с новостями")
public class NewsController {

    private final NewsService newsService;

    @Operation(summary = "Все новости с краткой информацией")
    @JsonView(NewsView.ShortInfo.class)
    @GetMapping("/")
    public ResponseEntity<List<NewsDto>> getAllProducts() {
        List<NewsDto> news = newsService.getAllNews();
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @Operation(summary = "Полная информация о определенной новости")
    @JsonView(NewsView.FullInfo.class)
    @GetMapping("/{id}")
    public NewsDto getOneNews(@PathVariable("id") @Min(0) Long newsId) {
        return newsService.getNewsById(newsId);
    }

    @Operation(summary = "Создание новости")
    @PostMapping("/news_creation")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNews(@RequestBody @Valid NewsDto news) {
        newsService.createNews(news);
    }

    @Operation(summary = "Редактирование новости")
    @PutMapping("/{id}/news_editing")
    public void updateNews(
            @PathVariable("id") @Min(0) Long newsId,
            @RequestBody @Valid NewsDto news) {
        newsService.updateNews(newsId, news);
    }

    @Operation(summary = "Удаление новости")
    @DeleteMapping("/{id}/news_deleting")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteNews(@PathVariable("id") @Min(0) Long newsId) {
        newsService.deleteNews(newsId);
        return ResponseEntity.noContent().build();
    }
}
