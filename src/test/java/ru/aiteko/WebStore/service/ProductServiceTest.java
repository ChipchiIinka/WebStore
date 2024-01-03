package ru.aiteko.WebStore.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.aiteko.WebStore.dto.request.ProductPageRequestDto;
import ru.aiteko.WebStore.entity.Products;
import ru.aiteko.WebStore.repository.ProductRepository;
import ru.aiteko.WebStore.repository.UserRepository;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import ru.aiteko.WebStore.entity.Users;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetAllProducts() {
        ProductPageRequestDto request = new ProductPageRequestDto();
        request.setPage(0);
        request.setSize(10);
        Page<Products> expectedPage = new PageImpl<>(Collections.singletonList(new Products()));
        when(productRepository.findAll(PageRequest.of(request.getPage(), request.getSize()))).thenReturn(expectedPage);
        Page<Products> result = productService.getAllProducts(request);
        assertEquals(expectedPage, result);
    }

    @Test
    public void testGetProductById() {
        Products expectedProduct = new Products();
        expectedProduct.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(expectedProduct));
        Products result = productService.getProductById(1L);
        assertEquals(expectedProduct, result);
    }

    @Test
    public void testCreateProduct() {
        Products productToCreate = new Products();
        productToCreate.setTitle("Test Product");
        Users adminUser = new Users();
        adminUser.setUsername("admin");
        when(userRepository.findByUsername("admin")).thenReturn(adminUser);
        when(productRepository.save(any(Products.class))).thenReturn(productToCreate);
        Products result = productService.createProduct(productToCreate);
        assertEquals("Test Product", result.getTitle());
        assertEquals(adminUser, result.getUser());
        assertNotNull(result.getCreation_date());
    }

    @Test
    public void testUpdateProduct() {
        Products existingProduct = new Products();
        existingProduct.setId(1L);
        existingProduct.setTitle("Existing Product");
        Products updatedProduct = new Products();
        updatedProduct.setTitle("Updated Product");
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Products.class))).thenReturn(updatedProduct);
        Products result = productService.updateProduct(1L, updatedProduct);
        assertEquals("Updated Product", result.getTitle());
    }

    @Test
    public void testDeleteProduct() {
        Products existingProduct = new Products();
        existingProduct.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).delete(existingProduct);
    }

    @Test
    public void testFindProductsByTitle() {
        String title = "Test";
        List<Products> expectedProducts = Collections.singletonList(new Products());
        when(productRepository.findByTitleContaining(title)).thenReturn(expectedProducts);
        List<Products> result = productService.findProductsByTitle(title);
        assertEquals(expectedProducts, result);
    }
}


