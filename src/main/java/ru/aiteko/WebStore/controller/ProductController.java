package ru.aiteko.WebStore.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.aiteko.WebStore.dto.ProductDto;
import ru.aiteko.WebStore.dto.views.ProductView;
import ru.aiteko.WebStore.service.ProductService;

import java.util.List;

@Validated
@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Tag(name = "Сервис для работы с товарами")
public class ProductController {

    private final ProductService productService;

    @JsonView(ProductView.AverageInfo.class)
    @Operation(summary = "Получение каталога товаров")
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @JsonView(ProductView.ShortInfo.class)
    @Operation(summary = "Поиск товара по имени")
    @GetMapping("/products/search")
    public ResponseEntity<List<ProductDto>> searchProductsByName(@RequestParam String title) {
        List<ProductDto> foundProducts = productService.findProductsByTitle(title);
        return new ResponseEntity<>(foundProducts, HttpStatus.OK);
    }

    @Operation(summary = "Полная информация о определенном товаре")
    @JsonView(ProductView.FullInfo.class)
    @GetMapping("/products/{id}")
    public ProductDto getOneProduct(@PathVariable("id") @Min(0) Long productId) {
        return productService.getProductById(productId);
    }

    @Operation(summary = "Создание нового товара")
    @PostMapping("/products/creation")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody @Valid ProductDto product) {
        productService.createProduct(product);
    }

    @Operation(summary = "Редактирование товара")
    @PutMapping("/products/{id}/editing")
    public void updateProduct(
            @PathVariable("id") @Min(0) Long productId,
            @RequestBody @Valid ProductDto product) {
        productService.updateProduct(productId, product);
    }

    @Operation(summary = "Удаление товара")
    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") @Min(0) Long productId) {
        productService.deleteProduct(productId);
    }
}