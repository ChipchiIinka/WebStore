//package ru.aiteko.WebStore.service;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import ru.aiteko.WebStore.dto.NewsDto;
//import ru.aiteko.WebStore.entity.NewsAndPromotions;
//import ru.aiteko.WebStore.repository.NewsRepository;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class NewsServiceTest {
//
//    @Mock
//    private NewsRepository newsRepository;
//
//    @InjectMocks
//    private NewsService newsService;
//
//    @Test
//    public void testGetAllNews() {
//        NewsAndPromotions news1 = new NewsAndPromotions();
//        NewsAndPromotions news2 = new NewsAndPromotions();
//        List<NewsAndPromotions> expectedNewsList = Arrays.asList(news1, news2);
//        when(newsRepository.findAll()).thenReturn(expectedNewsList);
//        List<NewsDto> result = newsService.getAllNews();
//        assertEquals(expectedNewsList, result);
//    }
//
//    @Test
//    public void testGetNewsById() {
//        NewsAndPromotions expectedNews = new NewsAndPromotions();
//        expectedNews.setId(1L);
//        when(newsRepository.findById(1L)).thenReturn(Optional.of(expectedNews));
//        NewsDto result = newsService.getNewsById(1L);
//        assertEquals(expectedNews, result);
//    }
//
//    @Test
//    public void testCreateNews() {
//        NewsAndPromotions newsToCreate = new NewsAndPromotions();
//        newsToCreate.setTitle("Test News");
//        when(newsRepository.save(any(NewsAndPromotions.class))).thenReturn(newsToCreate);
//        NewsAndPromotions result = newsService.createNews(newsToCreate);
//        assertEquals("Test News", result.getTitle());
//        assertNotNull(result.getCreation_date());
//    }
//
//    @Test
//    public void testUpdateNews() {
//        NewsAndPromotions existingNews = new NewsAndPromotions();
//        existingNews.setId(1L);
//        existingNews.setTitle("Existing News");
//        NewsAndPromotions updatedNews = new NewsAndPromotions();
//        updatedNews.setTitle("Updated News");
//        when(newsRepository.findById(1L)).thenReturn(Optional.of(existingNews));
//        when(newsRepository.save(any(NewsAndPromotions.class))).thenReturn(updatedNews);
//        NewsAndPromotions result = newsService.updateNews(1L, updatedNews);
//        assertEquals("Updated News", result.getTitle());
//    }
//
//    @Test
//    public void testDeleteNews() {
//        NewsAndPromotions existingNews = new NewsAndPromotions();
//        existingNews.setId(1L);
//        when(newsRepository.findById(1L)).thenReturn(Optional.of(existingNews));
//        newsService.deleteNews(1L);
//        verify(newsRepository, times(1)).delete(existingNews);
//    }
//}
//
//
