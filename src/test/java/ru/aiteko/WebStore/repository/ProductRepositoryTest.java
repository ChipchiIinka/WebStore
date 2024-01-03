package ru.aiteko.WebStore.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aiteko.WebStore.entity.Products;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testFindByTitleContaining() {
        String keyword = "test";
        Products product1 = new Products();
        product1.setId(1L);
        product1.setTitle("Test Product 1");
        Products product2 = new Products();
        product2.setId(2L);
        product2.setTitle("Another Test Product");
        List<Products> productList = Arrays.asList(product1, product2);
        when(productRepository.findByTitleContaining(keyword)).thenReturn(productList);
        List<Products> result = productRepository.findByTitleContaining(keyword);
        assertEquals(2, result.size());
        assertEquals("Test Product 1", result.get(0).getTitle());
        assertEquals("Another Test Product", result.get(1).getTitle());
    }
}
