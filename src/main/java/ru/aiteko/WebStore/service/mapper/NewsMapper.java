package ru.aiteko.WebStore.service.mapper;

import org.springframework.stereotype.Service;
import ru.aiteko.WebStore.dto.NewsDto;
import ru.aiteko.WebStore.entity.NewsAndPromotions;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsMapper {
    public List<NewsDto> toListDto(List<NewsAndPromotions> news) {
        return news.stream().map(this::toDto).toList();
    }

    public NewsDto toDto(NewsAndPromotions news){
        return NewsDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .description(news.getDescription())
                .creationDate(news.getCreationDate())
                .build();
    }

    public NewsAndPromotions toEntity(NewsDto newsDto){
        NewsAndPromotions news = new NewsAndPromotions();

        news.setTitle(newsDto.getTitle());
        news.setDescription(newsDto.getDescription());
        news.setCreationDate(LocalDateTime.now());

        return news;
    }
}
