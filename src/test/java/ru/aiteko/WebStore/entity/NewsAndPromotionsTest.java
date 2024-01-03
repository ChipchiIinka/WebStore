package ru.aiteko.WebStore.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class NewsAndPromotionsTest {

    @Test
    void testCreateNewsAndPromotions() {
        NewsAndPromotions news = new NewsAndPromotions();
        news.setTitle("Example title");
        news.setDescription("Example description");
        news.setCreation_date(LocalDateTime.now());
        assertEquals("Example title", news.getTitle());
        assertEquals("Example description", news.getDescription());
        assertNotNull(news.getCreation_date());
    }
}
