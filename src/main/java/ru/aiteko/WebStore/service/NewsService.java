package ru.aiteko.WebStore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.aiteko.WebStore.dto.NewsDto;
import ru.aiteko.WebStore.dto.request.NewsPageDto;
import ru.aiteko.WebStore.entity.NewsAndPromotions;
import ru.aiteko.WebStore.exception.AitekoException;
import ru.aiteko.WebStore.exception.ErrorType;
import ru.aiteko.WebStore.repository.NewsRepository;
import ru.aiteko.WebStore.service.mapper.NewsMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public List<NewsDto> getAllNews() {
        log.info("Fetching all news");

        return newsMapper.toListDto(newsRepository.findAll());
    }

    public NewsDto getNewsById(Long newsId) {
        log.info("Fetching news by ID: {}", newsId);

        NewsAndPromotions news = newsRepository.findById(newsId)
                .orElseThrow(() -> new AitekoException(ErrorType.NOT_FOUND,
                        "Новость или акция c ID " + newsId + " не найдена"));
        return newsMapper.toDto(news);
    }

    public void createNews(NewsDto newsDto) {
        log.info("Creating a new news: {}", newsDto);

        NewsAndPromotions news = newsMapper.toEntity(newsDto);
        newsRepository.save(news);
    }

    public void updateNews(Long newsId, NewsDto updatedNews) {
        log.info("Updating news with ID: {}. New news details: {}", newsId, updatedNews);

        NewsAndPromotions newsFromDb = newsRepository.findById(newsId)
                .orElseThrow(() -> new AitekoException(ErrorType.NOT_FOUND,
                        "Новость или акция c ID " + newsId + " не найдена"));
        newsFromDb.setTitle(updatedNews.getTitle());
        newsFromDb.setDescription(updatedNews.getDescription());

        newsRepository.save(newsFromDb);
    }

    public void deleteNews(Long newsId) {
        log.info("Deleting news with ID: {}", newsId);

        newsRepository.deleteById(newsId);
    }
}