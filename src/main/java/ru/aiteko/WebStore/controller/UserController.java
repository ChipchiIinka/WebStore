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
import ru.aiteko.WebStore.dto.UserDto;
import ru.aiteko.WebStore.dto.views.UserView;
import ru.aiteko.WebStore.entity.Products;
import ru.aiteko.WebStore.service.UserService;

import java.util.Set;

@Validated
@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Tag(name = "Сервис для работы с пользователем")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Просмотр странички пользователя")
    @JsonView(UserView.ShortInfo.class)
    @GetMapping("/user/{id}")
    public UserDto getUserProfile(@PathVariable("id") @Min(0) Long userId) {
        return userService.getUserById(userId);
    }

    @Operation(summary = "Добавление товара в корзину пользователя")
    @PostMapping("product/{productId}")
    public ResponseEntity<String> addToCart(@PathVariable @Min(0) long productId) {
        userService.addToShoppingCart(productId);
        return ResponseEntity.ok("Товар успешно добавлен в вашу корзину");
    }

    @Operation(summary = "Просмотр корзины пользователя")
    @GetMapping("user/{userId}/shoppingCart")
    public ResponseEntity<Set<Products>> getUserShoppingCart(@PathVariable @Min(0) long userId) {
        Set<Products> userShoppingCartProducts = userService.getUsersShoppingCartProduct();
        return new ResponseEntity<>(userShoppingCartProducts, HttpStatus.OK);
    }
}
