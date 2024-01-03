package ru.aiteko.WebStore.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aiteko.WebStore.entity.ShoppingCarts;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartRepositoryTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Test
    public void testFindAll() {
        ShoppingCarts cart1 = new ShoppingCarts();
        cart1.setId(1L);
        ShoppingCarts cart2 = new ShoppingCarts();
        cart2.setId(2L);
        List<ShoppingCarts> cartList = Arrays.asList(cart1, cart2);
        when(shoppingCartRepository.findAll()).thenReturn(cartList);
        List<ShoppingCarts> result = shoppingCartRepository.findAll();
        assertEquals(2, result.size());
    }
}

