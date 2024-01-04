package ru.aiteko.WebStore.controller;

import ru.aiteko.WebStore.entity.Users;
import ru.aiteko.WebStore.entity.views.UserView;
import ru.aiteko.WebStore.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Сервис для работы с пользователем")
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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
}
