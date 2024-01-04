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
import ru.aiteko.WebStore.entity.Products;
import ru.aiteko.WebStore.entity.views.ProductView;
import ru.aiteko.WebStore.service.ProductService;
import java.util.List;

@Tag(name = "Сервис для работы с товарами")
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

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
}