//package ru.aiteko.WebStore.conroller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import ru.aiteko.WebStore.dto.request.ProductPageRequestDto;
//import ru.aiteko.WebStore.entity.Products;
//import ru.aiteko.WebStore.service.ProductService;
//import java.util.Collections;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ProductControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private ProductService productService;
//
//    @Test
//    public void getAllProductsTest() throws Exception {
//        ProductPageRequestDto requestDto = new ProductPageRequestDto();
//        requestDto.setPage(0);
//        requestDto.setSize(10);
//        mockMvc.perform(MockMvcRequestBuilders.get("/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("page", "0")
//                        .param("size", "10"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void searchProductsByNameTest() throws Exception {
//        when(productService.findProductsByTitle(any(String.class)))
//                .thenReturn(Collections.singletonList(new Products()));
//        mockMvc.perform(get("/products/search").param("title", "SampleProduct"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void getOneProductTest() throws Exception {
//        when(productService.getProductById(any(Long.class)))
//                .thenReturn(new Products());
//        mockMvc.perform(get("/products/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void createProductTest() throws Exception {
//        Products product = new Products();
//        product.setTitle("SampleProduct");
//        when(productService.createProduct(any(Products.class)))
//                .thenReturn(product);
//        mockMvc.perform(post("/products/creation")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(product)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void updateProductTest() throws Exception {
//        Products existingProduct = new Products();
//        existingProduct.setId(1L);
//        when(productService.updateProduct(any(Long.class), any(Products.class)))
//                .thenReturn(existingProduct);
//        mockMvc.perform(post("/products/1/editing")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(existingProduct)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void deleteProductTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/products/1/deleting")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//}
//
