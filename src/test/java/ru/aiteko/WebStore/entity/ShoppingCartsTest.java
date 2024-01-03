package ru.aiteko.WebStore.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartsTest {

    @Test
    void testCreateShoppingCarts() {
        ShoppingCarts shoppingCart = new ShoppingCarts();
        assertNotNull(shoppingCart);
    }

    @Test
    void testSetUser() {
        ShoppingCarts shoppingCart = new ShoppingCarts();
        Users user = new Users();
        shoppingCart.setUser(user);
        assertEquals(user, shoppingCart.getUser());
    }

    @Test
    void testAddProductToCart() {
        ShoppingCarts shoppingCart = new ShoppingCarts();
        Products product = new Products();
        assertTrue(shoppingCart.getProducts().isEmpty());
        shoppingCart.getProducts().add(product);
        assertFalse(shoppingCart.getProducts().isEmpty());
        assertTrue(shoppingCart.getProducts().contains(product));
    }

    @Test
    void testRemoveProductFromCart() {
        ShoppingCarts shoppingCart = new ShoppingCarts();
        Products product = new Products();
        shoppingCart.getProducts().add(product);
        assertTrue(shoppingCart.getProducts().contains(product));
        shoppingCart.getProducts().remove(product);
        assertFalse(shoppingCart.getProducts().contains(product));
    }
}

