package ru.aiteko.WebStore.conroller;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.aiteko.WebStore.controller.Controller;
import ru.aiteko.WebStore.dto.request.ProductPageRequestDto;
import ru.aiteko.WebStore.entity.NewsAndPromotions;
import ru.aiteko.WebStore.entity.Products;
import ru.aiteko.WebStore.entity.Users;
import ru.aiteko.WebStore.service.NewsService;
import ru.aiteko.WebStore.service.ProductService;
import ru.aiteko.WebStore.service.ShoppingCartService;
import ru.aiteko.WebStore.service.UserService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ControllerTest {

    @Mock
    private ProductService productService;
    @Mock
    private NewsService newsService;
    @Mock
    private UserService userService;
    @Mock
    private ShoppingCartService cartService;

    @InjectMocks
    private Controller controller;

    @Test
    void testGetAllProducts() {
        List<NewsAndPromotions> newsList = new ArrayList<>();
        when(newsService.getAllNews()).thenReturn(newsList);
        assertEquals(newsList, controller.getAllProducts().getBody());
        verify(newsService, times(1)).getAllNews();
    }

    @Test
    void testGetOneNews() {
        Long newsId = 1L;
        NewsAndPromotions expectedNews = new NewsAndPromotions();
        when(newsService.getNewsById(newsId)).thenReturn(expectedNews);
        assertEquals(expectedNews, controller.getOneNews(newsId));
        verify(newsService, times(1)).getNewsById(newsId);
    }

    @Test
    void testCreateNews() {
        NewsAndPromotions newsToCreate = new NewsAndPromotions();
        when(newsService.createNews(newsToCreate)).thenReturn(newsToCreate);
        assertEquals(newsToCreate, controller.createNews(newsToCreate));
        verify(newsService, times(1)).createNews(newsToCreate);
    }

    @Test
    void testUpdateNews() {
        Long newsId = 1L;
        NewsAndPromotions newsToUpdate = new NewsAndPromotions();
        when(newsService.updateNews(newsId, newsToUpdate)).thenReturn(newsToUpdate);
        assertEquals(newsToUpdate, controller.updateNews(newsId, newsToUpdate));
        verify(newsService, times(1)).updateNews(newsId, newsToUpdate);
    }

    @Test
    void testDeleteNews() {
        Long newsId = 1L;
        controller.deleteNews(newsId);
        verify(newsService, times(1)).deleteNews(newsId);
    }

    @Test
    void testGetAllProductsPage() {
        ProductPageRequestDto request = new ProductPageRequestDto();
        Page<Products> productsPage = mock(Page.class);
        when(productService.getAllProducts(request)).thenReturn(productsPage);
        assertEquals(productsPage, controller.getAllProducts(request).getBody());
        verify(productService, times(1)).getAllProducts(request);
    }

    @Test
    void testSearchProductsByName() {
        String title = "SomeProduct";
        List<Products> foundProducts = new ArrayList<>();
        when(productService.findProductsByTitle(title)).thenReturn(foundProducts);
        assertEquals(foundProducts, controller.searchProductsByName(title).getBody());
        verify(productService, times(1)).findProductsByTitle(title);
    }

    @Test
    void testCreateProduct() {
        Products productToCreate = new Products();
        when(productService.createProduct(productToCreate)).thenReturn(productToCreate);
        assertEquals(productToCreate, controller.createProduct(productToCreate));
        verify(productService, times(1)).createProduct(productToCreate);
    }

    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        Products productToUpdate = new Products();
        when(productService.updateProduct(productId, productToUpdate)).thenReturn(productToUpdate);
        assertEquals(productToUpdate, controller.updateProduct(productId, productToUpdate));
        verify(productService, times(1)).updateProduct(productId, productToUpdate);
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;
        controller.deleteProduct(productId);
        verify(productService, times(1)).deleteProduct(productId);
    }

    @Test
    void testGetUserProfile() {
        Long userId = 1L;
        Users expectedUser = new Users();
        when(userService.getUserById(userId)).thenReturn(expectedUser);
        assertEquals(expectedUser, controller.getUserProfile(userId));
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void testRegistration() {
        Users userToRegister = new Users();
        when(userService.register(userToRegister)).thenReturn(userToRegister);
        assertEquals(userToRegister, controller.registration(userToRegister));
        verify(userService, times(1)).register(userToRegister);
    }

    @Test
    void testGetUserShoppingCart() {
        long userId = 1L;
        Set<Products> expectedProducts = new HashSet<>();
        when(cartService.getUserShoppingCartProducts(userId)).thenReturn(expectedProducts);
        assertEquals(expectedProducts, controller.getUserShoppingCart(userId).getBody());
        verify(cartService, times(1)).getUserShoppingCartProducts(userId);
    }

    @Test
    void testAddToCart() {
        long productId = 1L;
        long userId = 1L;
        ResponseEntity<String> responseEntity = controller.addToCart(productId, userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Product added to the shopping cart successfully.", responseEntity.getBody());
        verify(cartService, times(1)).addToShoppingCart(userId, productId);
    }
}

