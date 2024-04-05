package ru.aiteko.WebStore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aiteko.WebStore.dto.LoginDto;
import ru.aiteko.WebStore.dto.SignUpDto;
import ru.aiteko.WebStore.service.AuthService;

@Validated
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Сервис для авторизации")
public class AuthController {

    private final AuthService userService;

    @Operation(summary = "Регистрация нового пользователя")
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto signUpDto) {
        userService.signUp(signUpDto);
        return new ResponseEntity<>("Пользователь успешно зарегистрирован", HttpStatus.OK);
    }

    @Operation(summary = "Авторизация")
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        userService.authenticateUser(loginDto);
        return new ResponseEntity<>("Вы успешно авторизировались!", HttpStatus.OK);
    }
}
