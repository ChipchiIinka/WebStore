package ru.aiteko.WebStore.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.aiteko.WebStore.entity.Role;
import ru.aiteko.WebStore.entity.ShoppingCarts;
import ru.aiteko.WebStore.entity.Users;
import ru.aiteko.WebStore.repository.ShoppingCartRepository;
import ru.aiteko.WebStore.repository.UserRepository;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ShoppingCartRepository cartRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testRegisterUser() {
        Users userToRegister = new Users();
        userToRegister.setUsername("testUser");
        userToRegister.setPassword("password");
        when(userRepository.save(any(Users.class))).thenReturn(userToRegister);
        Users result = userService.register(userToRegister);
        assertEquals("testUser", result.getUsername());
        assertEquals(Collections.singleton(Role.USER), result.getRole());
        verify(cartRepository, times(1)).save(any(ShoppingCarts.class));
    }

    @Test
    public void testGetUserById() {
        Users expectedUser = new Users();
        expectedUser.setId(1L);
        expectedUser.setUsername("testUser");
        when(userRepository.findById(1L)).thenReturn(Optional.of(expectedUser));
        Users result = userService.getUserById(1L);
        assertEquals(expectedUser, result);
    }
}

