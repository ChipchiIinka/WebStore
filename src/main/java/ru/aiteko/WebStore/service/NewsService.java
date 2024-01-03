package ru.aiteko.WebStore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.aiteko.WebStore.entity.NewsAndPromotions;
import ru.aiteko.WebStore.repository.NewsRepository;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public List<NewsAndPromotions> getAllNews() {
        log.info("Fetching all products");
        return newsRepository.findAll();
    }

    public NewsAndPromotions getNewsById(Long newsId) {
        log.info("Fetching news by ID: {}", newsId);
        return newsRepository.findById(newsId)
                .orElse(null);
    }

    public NewsAndPromotions createNews(NewsAndPromotions news) {
        log.info("Creating a new news: {}", news);
        news.setCreation_date(LocalDateTime.now());
        return newsRepository.save(news);
    }

    public NewsAndPromotions updateNews(Long newsId, NewsAndPromotions updatedNews) {
        log.info("Updating news with ID: {}. New news details: {}", newsId, updatedNews);
        NewsAndPromotions newsFromDb = getNewsById(newsId);
        BeanUtils.copyProperties(updatedNews, newsFromDb, "id");
        return newsRepository.save(newsFromDb);
    }

    public void deleteNews(Long newsId) {
        log.info("Deleting news with ID: {}", newsId);
        NewsAndPromotions news= getNewsById(newsId);
        newsRepository.delete(news);
    }
}
