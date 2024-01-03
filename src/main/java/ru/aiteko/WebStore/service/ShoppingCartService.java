package ru.aiteko.WebStore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.aiteko.WebStore.entity.Products;
import ru.aiteko.WebStore.entity.ShoppingCartProducts;
import ru.aiteko.WebStore.entity.ShoppingCarts;
import ru.aiteko.WebStore.entity.Users;
import ru.aiteko.WebStore.repository.ProductRepository;
import ru.aiteko.WebStore.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void addToShoppingCart(long userId, long productId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + productId + " not found"));
        ShoppingCarts shoppingCart = user.getShoppingCarts().iterator().next(); // Предполагаем, что у пользователя всегда есть корзина
        ShoppingCartProducts cartProduct = new ShoppingCartProducts();
        cartProduct.setShoppingCart(shoppingCart);
        cartProduct.setProduct(product);
        shoppingCart.getProducts().add(cartProduct.getProduct());
        userRepository.save(user);
    }
    public Set<Products> getUserShoppingCartProducts(long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));
        // Получаем корзину пользователя
        Set<ShoppingCarts> userShoppingCarts = user.getShoppingCarts();
        if (userShoppingCarts != null && !userShoppingCarts.isEmpty()) {
            ShoppingCarts userShoppingCart = userShoppingCarts.iterator().next();
            // Получаем продукты из корзины пользователя
            Set<Products> userShoppingCartProducts = userShoppingCart.getProducts();
            return userShoppingCartProducts != null ? userShoppingCartProducts : new HashSet<>();
        } else {
            // Если у пользователя нет корзины, возвращаем пустоту
            return new HashSet<>();
        }
    }
}