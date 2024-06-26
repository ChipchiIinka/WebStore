//package ru.aiteko.WebStore.service;
//
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import ru.aiteko.WebStore.entity.Products;
//import ru.aiteko.WebStore.entity.ShoppingCarts;
//import ru.aiteko.WebStore.entity.Users;
//import ru.aiteko.WebStore.repository.ProductRepository;
//import ru.aiteko.WebStore.repository.UserRepository;
//import java.util.HashSet;
//import java.util.Set;
//
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class ShoppingCartService {
//
//    private final UserRepository userRepository;
//    private final ProductRepository productRepository;
//
//    public void addToShoppingCart(long userId, long productId) {
//        Users user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));
//        Products product = productRepository.findById(productId)
//                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + productId + " not found"));
//        // Находим корзину пользователя
//        Set<ShoppingCarts> userShoppingCart = user.getShoppingCarts();
//        if (userShoppingCart.isEmpty()) {
//            // Если у пользователя нет корзины, создаем новую
//            ShoppingCarts shoppingCart = new ShoppingCarts();
//            shoppingCart.setUser(user);
//            userShoppingCart.add(shoppingCart);
//        }
//
//        ShoppingCarts shoppingCart = userShoppingCart.iterator().next();
//        shoppingCart.getProducts().add(product);
//        userRepository.save(user);
//    }
//
//    public Set<Products> getUserShoppingCartProducts(long userId) {
//        Users user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));
//        // Получаем корзину пользователя
//        Set<ShoppingCarts> userShoppingCarts = user.getShoppingCarts();
//        if (userShoppingCarts != null && !userShoppingCarts.isEmpty()) {
//            ShoppingCarts userShoppingCart = userShoppingCarts.iterator().next();
//            // Получаем продукты из корзины пользователя
//            Set<Products> userShoppingCartProducts = userShoppingCart.getProducts();
//            return userShoppingCartProducts != null ? userShoppingCartProducts : new HashSet<>();
//        } else {
//            // Если у пользователя нет корзины, возвращаем пустоту
//            return new HashSet<>();
//        }
//    }
//}