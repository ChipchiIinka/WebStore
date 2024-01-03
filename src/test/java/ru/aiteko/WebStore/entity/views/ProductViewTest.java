package ru.aiteko.WebStore.entity.views;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductViewTest {

    @Test
    void testShortInfoInterface() {
        ProductView.ShortInfo shortInfo = new ProductView.ShortInfo() {};
        assertNotNull(shortInfo);
    }

    @Test
    void testAverageInfoInterface() {
        ProductView.AverageInfo averageInfo = new ProductView.AverageInfo() {};
        assertNotNull(averageInfo);
        assertTrue(averageInfo instanceof ProductView.ShortInfo);
    }

    @Test
    void testFullInfoInterface() {
        ProductView.FullInfo fullInfo = new ProductView.FullInfo() {};
        assertNotNull(fullInfo);
        assertTrue(fullInfo instanceof ProductView.AverageInfo);
        assertTrue(fullInfo instanceof ProductView.ShortInfo);
    }
}

