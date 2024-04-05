//package ru.aiteko.WebStore.conroller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.mockito.stubbing.OngoingStubbing;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import ru.aiteko.WebStore.dto.NewsDto;
//import ru.aiteko.WebStore.entity.NewsAndPromotions;
//import ru.aiteko.WebStore.service.NewsService;
//import java.util.Collections;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class NewsControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private NewsService newsService;
//
//    @Test
//    public void getAllNewsTest() throws Exception {
//        NewsAndPromotions news = new NewsAndPromotions();
//        Mockito.when(newsService.getAllNews()).thenReturn(Collections.singletonList(NewsDto.builder().build()));
//        mockMvc.perform(MockMvcRequestBuilders.get("/")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").doesNotExist());
//    }
//
//    @Test
//    public void getOneNewsTest() throws Exception {
//        Long newsId = 1L;
//        NewsAndPromotions news = new NewsAndPromotions();
//        Mockito.when(newsService.getNewsById(newsId)).thenReturn(NewsDto.builder().build());
//        mockMvc.perform(MockMvcRequestBuilders.get("/{id}", newsId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist());
//    }
//
//    @Test
//    public void createNewsTest() throws Exception {
//        NewsAndPromotions newsToCreate = new NewsAndPromotions();
//        NewsAndPromotions createdNews = new NewsAndPromotions();
//        createdNews.setId(1L);
//        mockMvc.perform(MockMvcRequestBuilders.post("/news_creation")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(newsToCreate)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L)); // ожидаем, что id не равен 0
//    }
//
//    @Test
//    public void updateNewsTest() throws Exception {
//        // Arrange
//        Long newsId = 1L;
//        NewsAndPromotions existingNews = new NewsAndPromotions();
//        NewsAndPromotions updatedNews = new NewsAndPromotions();
//        updatedNews.setId(newsId);
//        mockMvc.perform(MockMvcRequestBuilders.post("/{id}/news_editing", newsId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(existingNews)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(newsId)); // ожидаем, что id не равен 0
//    }
//
//    @Test
//    public void deleteNewsTest() throws Exception {
//        Long newsId = 1L;
//        mockMvc.perform(MockMvcRequestBuilders.delete("/{id}/news_deleting", newsId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//    }
//}
