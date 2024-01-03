package ru.aiteko.WebStore.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aiteko.WebStore.entity.NewsAndPromotions;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewsRepositoryTest {

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private NewsAndPromotions newsAndPromotions;

    @Test
    public void testSaveNews() {
        NewsAndPromotions news1 = new NewsAndPromotions();
        news1.setId(1L);
        news1.setTitle("Test News 1");
        NewsAndPromotions news2 = new NewsAndPromotions();
        news2.setId(2L);
        news2.setTitle("Another Test News");
        List<NewsAndPromotions> newsList = Arrays.asList(news1, news2);
        when(newsRepository.saveAll(newsList)).thenReturn(newsList);
        List<NewsAndPromotions> savedNews = newsRepository.saveAll(newsList);
        assertEquals(2, savedNews.size());
        assertEquals("Test News 1", savedNews.get(0).getTitle());
        assertEquals("Another Test News", savedNews.get(1).getTitle());
    }

    @Test
    public void testFindAllNews() {
        NewsAndPromotions news1 = new NewsAndPromotions();
        news1.setId(1L);
        news1.setTitle("Test News 1");
        NewsAndPromotions news2 = new NewsAndPromotions();
        news2.setId(2L);
        news2.setTitle("Another Test News");
        List<NewsAndPromotions> newsList = Arrays.asList(news1, news2);
        when(newsRepository.findAll()).thenReturn(newsList);
        List<NewsAndPromotions> allNews = newsRepository.findAll();
        assertEquals(2, allNews.size());
        assertEquals("Test News 1", allNews.get(0).getTitle());
        assertEquals("Another Test News", allNews.get(1).getTitle());
    }
}

