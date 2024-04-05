//package ru.aiteko.WebStore.service;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import ru.aiteko.WebStore.entity.Products;
//import ru.aiteko.WebStore.entity.ShoppingCarts;
//import ru.aiteko.WebStore.entity.Users;
//import ru.aiteko.WebStore.repository.ProductRepository;
//import ru.aiteko.WebStore.repository.UserRepository;
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class ShoppingCartServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @InjectMocks
//    private ShoppingCartService shoppingCartService;
//
//    @Test
//    public void testAddToShoppingCart() {
//        Users user = new Users();
//        user.setId(1L);
//        Products product = new Products();
//        product.setId(1L);
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
//        shoppingCartService.addToShoppingCart(1L, 1L);
//        verify(userRepository, times(1)).save(user);
//        assertEquals(1, user.getShoppingCarts().iterator().next().getProducts().size());
//    }
//
//    @Test
//    public void testGetUserShoppingCartProducts() {
//        Users user = new Users();
//        user.setId(1L);
//        ShoppingCarts shoppingCart = new ShoppingCarts();
//        Products product1 = new Products();
//        product1.setId(1L);
//        Products product2 = new Products();
//        product2.setId(2L);
//        Set<Products> productsSet = new HashSet<>();
//        productsSet.add(product1);
//        productsSet.add(product2);
//        shoppingCart.setProducts(productsSet);
//        user.getShoppingCarts().add(shoppingCart);
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//        Set<Products> result = shoppingCartService.getUserShoppingCartProducts(1L);
//        assertEquals(2, result.size());
//    }
//}
