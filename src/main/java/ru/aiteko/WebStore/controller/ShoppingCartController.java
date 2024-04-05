//package ru.aiteko.WebStore.controller;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ru.aiteko.WebStore.entity.Products;
//import ru.aiteko.WebStore.service.ShoppingCartService;
//import java.util.Set;
//
//@Tag(name = "Сервис для работы с корзиной")
//@RestController
//@RequestMapping("")
//@RequiredArgsConstructor
//public class ShoppingCartController {
//
//    private final ShoppingCartService cartService;
//
//    @Operation(summary = "Просмотр корзины пользователя")
//    @GetMapping("profile/{userId}/shoppingCart")
//    public ResponseEntity<Set<Products>> getUserShoppingCart(@PathVariable long userId) {
//        Set<Products> userShoppingCartProducts = cartService.getUserShoppingCartProducts(userId);
//        return new ResponseEntity<>(userShoppingCartProducts, HttpStatus.OK);
//    }
//
//    @Operation(summary = "Добавление товара в корзину")
//    @PostMapping("product/{productId}/addToCart")
//    public ResponseEntity<String> addToCart(@PathVariable long productId, @RequestParam long userId) {
//        cartService.addToShoppingCart(userId, productId);
//        return ResponseEntity.ok("Product added to the shopping cart successfully.");
//    }
//}
