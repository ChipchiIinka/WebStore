package ru.aiteko.WebStore.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartProductsTest {

    @Test
    void testCreateShoppingCartProducts() {
        ShoppingCartProducts cartProducts = new ShoppingCartProducts();
        assertNotNull(cartProducts);
    }

    @Test
    void testSetShoppingCart() {
        ShoppingCartProducts cartProducts = new ShoppingCartProducts();
        ShoppingCarts shoppingCart = new ShoppingCarts();
        cartProducts.setShoppingCart(shoppingCart);
        assertEquals(shoppingCart, cartProducts.getShoppingCart());
    }

    @Test
    void testSetProduct() {
        ShoppingCartProducts cartProducts = new ShoppingCartProducts();
        Products product = new Products();
        cartProducts.setProduct(product);
        assertEquals(product, cartProducts.getProduct());
    }
}

