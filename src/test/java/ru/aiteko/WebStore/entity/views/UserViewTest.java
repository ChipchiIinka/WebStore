package ru.aiteko.WebStore.entity.views;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserViewTest {

    @Test
    void testShortInfoInterface() {
        UserView.ShortInfo shortInfo = new UserView.ShortInfo() {};
        assertNotNull(shortInfo);
    }
}
