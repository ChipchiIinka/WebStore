package ru.aiteko.WebStore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.aiteko.WebStore.entity.Role;
import ru.aiteko.WebStore.entity.ShoppingCarts;
import ru.aiteko.WebStore.entity.Users;
import ru.aiteko.WebStore.repository.ShoppingCartRepository;
import ru.aiteko.WebStore.repository.UserRepository;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

private final UserRepository userRepository;
private final ShoppingCartRepository cartRepository;


    public Users register(Users user) {
        log.info("Registering a new user: {}", user);
        user.setRole(Collections.singleton(Role.USER));
        Users savedUser = userRepository.save(user);
        // Create a new shopping cart for the user
        ShoppingCarts shoppingCart = new ShoppingCarts();
        shoppingCart.setUser(savedUser);
        cartRepository.save(shoppingCart);
        return savedUser;
    }

    public Users getUserById(Long userId) {
        log.info("Fetching product by ID: {}", userId);
        return userRepository.findById(userId)
                .orElse(null);
    }
}
