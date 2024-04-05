//package ru.aiteko.WebStore.conroller;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class ShoppingCartControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void getUserShoppingCartTest() throws Exception {
//        long userId = 1L;
//        mockMvc.perform(MockMvcRequestBuilders.get("/profile/{userId}/shoppingCart", userId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    void addToCartTest() throws Exception {
//        long productId = 1L;
//        long userId = 1L;
//        mockMvc.perform(MockMvcRequestBuilders.post("/product/{productId}/addToCart", productId)
//                        .param("userId", String.valueOf(userId))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("Product added to the shopping cart successfully."));
//    }
//}
//
