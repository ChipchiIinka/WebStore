package ru.aiteko.WebStore.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aiteko.WebStore.entity.NewsAndPromotions;
import ru.aiteko.WebStore.entity.views.NewsView;
import ru.aiteko.WebStore.service.NewsService;
import java.util.List;

@Tag(name = "Сервис для работы с новостями")
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @Operation(summary = "Все новости с краткой информацией")
    @JsonView(NewsView.ShortInfo.class)
    @GetMapping("/")
    public ResponseEntity<List<NewsAndPromotions>> getAllProducts() {
        List<NewsAndPromotions> news = newsService.getAllNews();
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @Operation(summary = "Полная информация о определенной новости")
    @JsonView(NewsView.FullInfo.class)
    @GetMapping("/{id}")
    public NewsAndPromotions getOneNews(@PathVariable("id") Long newsId) {
        return newsService.getNewsById(newsId);
    }

    @Operation(summary = "Создание новости")
    @PostMapping("/news_creation")
    public NewsAndPromotions createNews(@RequestBody NewsAndPromotions news) {
        return newsService.createNews(news);
    }

    @Operation(summary = "Редактирование новости")
    @PostMapping("/{id}/news_editing")
    public NewsAndPromotions updateNews(
            @PathVariable("id") Long newsId,
            @RequestBody NewsAndPromotions news) {
        return newsService.updateNews(newsId, news);
    }

    @Operation(summary = "Удаление новости")
    @DeleteMapping("/{id}/news_deleting")
    public ResponseEntity<Void> deleteNews(@PathVariable("id") Long newsId) {
        newsService.deleteNews(newsId);
        return ResponseEntity.noContent().build();
    }
}
