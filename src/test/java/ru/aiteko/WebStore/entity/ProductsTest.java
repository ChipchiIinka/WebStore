package ru.aiteko.WebStore.entity;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ProductsTest {

    @Test
    void testCreateProducts() {
        Products product = new Products();
        product.setTitle("Example title");
        product.setDescription("Example description");
        product.setCreation_date(LocalDateTime.now());
        product.setPrice(new BigDecimal("19.99"));
        assertEquals("Example title", product.getTitle());
        assertEquals("Example description", product.getDescription());
        assertNotNull(product.getCreation_date());
        assertEquals(new BigDecimal("19.99"), product.getPrice());
    }
}

