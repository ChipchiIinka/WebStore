package ru.aiteko.WebStore.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aiteko.WebStore.entity.Users;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        Users user = new Users();
        user.setId(1L);
        user.setUsername("testUser");
        when(userRepository.findByUsername("testUser")).thenReturn(user);
        Users result = userRepository.findByUsername("testUser");
        assertEquals("testUser", result.getUsername());
    }

    @Test
    public void testExistsByUsername() {
        when(userRepository.existsByUsername("admin")).thenReturn(true);
        boolean result = userRepository.existsByUsername("admin");
        assertEquals(true, result);
    }
}
