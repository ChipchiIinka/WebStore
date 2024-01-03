package ru.aiteko.WebStore.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aiteko.WebStore.dto.request.ProductPageRequestDto;
import ru.aiteko.WebStore.entity.NewsAndPromotions;
import ru.aiteko.WebStore.entity.Products;
import ru.aiteko.WebStore.entity.Users;
import ru.aiteko.WebStore.entity.views.NewsView;
import ru.aiteko.WebStore.entity.views.ProductView;
import ru.aiteko.WebStore.entity.views.UserView;
import ru.aiteko.WebStore.service.NewsService;
import ru.aiteko.WebStore.service.ProductService;
import ru.aiteko.WebStore.service.ShoppingCartService;
import ru.aiteko.WebStore.service.UserService;

import java.util.List;
import java.util.Set;

@Tag(name = "Сервис для просмотра товаров")
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class Controller {
    private final ProductService productService;
    private final NewsService newsService;
    private final UserService userService;
    private final ShoppingCartService cartService;

    /*
     * News page
     */
    @Operation(summary = "Все новости с краткой информацией")
    @JsonView(NewsView.ShortInfo.class)
    @GetMapping("/")
    public ResponseEntity<List<NewsAndPromotions>> getAllProducts() {
        List<NewsAndPromotions> news = newsService.getAllNews();
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @Operation(summary = "Полная информация о определенной новости")
    @JsonView(NewsView.FullInfo.class)
    @GetMapping("/{id}")
    public NewsAndPromotions getOneNews(@PathVariable("id") Long newsId) {
        return newsService.getNewsById(newsId);
    }

    @Operation(summary = "Создание новости")
    @PostMapping("/news_creation")
    public NewsAndPromotions createNews(@RequestBody NewsAndPromotions news) {
        return newsService.createNews(news);
    }

    @Operation(summary = "Редактирование новости")
    @PostMapping("/{id}/news_editing")
    public NewsAndPromotions updateNews(
            @PathVariable("id") Long newsId,
            @RequestBody NewsAndPromotions news) {
        return newsService.updateNews(newsId, news);
    }

    @Operation(summary = "Удаление новости")
    @DeleteMapping("/{id}/news_deleting")
    public void deleteNews(@PathVariable("id") Long newsId) {
        newsService.deleteNews(newsId);
    }

    /*
     * Product page
     */
    @Operation(summary = "Получение каталога товаров (по 10шт на страницу)")
    @GetMapping("/products")
    public ResponseEntity<Page<Products>> getAllProducts(@Valid @ModelAttribute ProductPageRequestDto request) {
        Page<Products> productsPage = productService.getAllProducts(request);
        return new ResponseEntity<>(productsPage, HttpStatus.OK);
    }

    @JsonView(ProductView.ShortInfo.class)
    @Operation(summary = "Поиск товара по имени")
    @GetMapping("/products/search")
    public ResponseEntity<List<Products>> searchProductsByName(@RequestParam String title) {
        List<Products> foundProducts = productService.findProductsByTitle(title);
        return new ResponseEntity<>(foundProducts, HttpStatus.OK);
    }

    @Operation(summary = "Полная информация о определенном товаре")
    @JsonView(ProductView.FullInfo.class)
    @GetMapping("/products/{id}")
    public Products getOneProduct(@PathVariable("id") Long productId) {
        return productService.getProductById(productId);
    }

    @Operation(summary = "Создание нового товара")
    @PostMapping("/products/creation")
    public Products createProduct(@RequestBody Products product) {
        return productService.createProduct(product);
    }

    @Operation(summary = "Редактирование товара")
    @PostMapping("/products/{id}/editing")
    public Products updateProduct(
            @PathVariable("id") Long productId,
            @RequestBody Products product) {
        return productService.updateProduct(productId, product);
    }

    @Operation(summary = "Удаление товара")
    @DeleteMapping("/products/{id}/deleting")
    public void deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
    }

    /*
     * User page
     */
    @Operation(summary = "Просмотр странички пользователя")
    @JsonView(UserView.ShortInfo.class)
    @GetMapping("/profile/{id}")
    public Users getUserProfile(@PathVariable("id") Long userId) {
        return userService.getUserById(userId);
    }

    @Operation(summary = "Регистрация нового пользователя")
    @PostMapping("/register")
    public Users registration(@RequestBody Users user) {
        return userService.register(user);
    }

    /*
     * All with shopping cart
     */
    @Operation(summary = "Просмотр корзины пользователя")
    @GetMapping("profile/{userId}/shoppingCart")
    public ResponseEntity<Set<Products>> getUserShoppingCart(@PathVariable long userId) {
        Set<Products> userShoppingCartProducts = cartService.getUserShoppingCartProducts(userId);
        return new ResponseEntity<>(userShoppingCartProducts, HttpStatus.OK);
    }

    @Operation(summary = "Добавление товара в корзину")
    @PostMapping("product/{productId}/addToCart")
    public ResponseEntity<String> addToCart(@PathVariable long productId, @RequestParam long userId) {
        cartService.addToShoppingCart(userId, productId);
        return ResponseEntity.ok("Product added to the shopping cart successfully.");
    }
}
