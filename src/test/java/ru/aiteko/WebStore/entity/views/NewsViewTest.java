package ru.aiteko.WebStore.entity.views;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NewsViewTest {

    @Test
    void testShortInfoInterface() {
        NewsView.ShortInfo shortInfo = new NewsView.ShortInfo() {};
        assertNotNull(shortInfo);
    }

    @Test
    void testFullInfoInterface() {
        NewsView.FullInfo fullInfo = new NewsView.FullInfo() {};
        assertNotNull(fullInfo);
        assertTrue(fullInfo instanceof NewsView.ShortInfo);
    }
}

