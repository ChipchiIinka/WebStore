package ru.aiteko.WebStore.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsersTest {

    @Test
    void testCreateUsers() {
        Users user = new Users();
        assertNotNull(user);
    }

    @Test
    void testSetUsername() {
        Users user = new Users();
        user.setUsername("ExampleUsername");
        assertEquals("ExampleUsername", user.getUsername());
    }

    @Test
    void testSetPassword() {
        Users user = new Users();
        user.setPassword("ExamplePassword");
        assertEquals("ExamplePassword", user.getPassword());
    }

    @Test
    void testSetEmail() {
        Users user = new Users();
        user.setEmail("example@example.com");
        assertEquals("example@example.com", user.getEmail());
    }

    @Test
    void testAddShoppingCart() {
        Users user = new Users();
        ShoppingCarts shoppingCart = new ShoppingCarts();
        assertTrue(user.getShoppingCarts().isEmpty());
        user.getShoppingCarts().add(shoppingCart);
        assertFalse(user.getShoppingCarts().isEmpty());
        assertTrue(user.getShoppingCarts().contains(shoppingCart));
    }

    @Test
    void testRemoveShoppingCart() {
        Users user = new Users();
        ShoppingCarts shoppingCart = new ShoppingCarts();
        user.getShoppingCarts().add(shoppingCart);
        assertTrue(user.getShoppingCarts().contains(shoppingCart));
        user.getShoppingCarts().remove(shoppingCart);
        assertFalse(user.getShoppingCarts().contains(shoppingCart));
    }
}

