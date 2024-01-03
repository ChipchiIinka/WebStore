package ru.aiteko.WebStore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.aiteko.WebStore.dto.request.ProductPageRequestDto;
import ru.aiteko.WebStore.entity.Products;
import ru.aiteko.WebStore.entity.Users;
import ru.aiteko.WebStore.repository.ProductRepository;
import ru.aiteko.WebStore.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Page<Products> getAllProducts(ProductPageRequestDto request) {
        log.info("Fetching all products with page: {} and size: {}", request.getPage(), request.getSize());
        return productRepository.findAll(PageRequest.of(request.getPage(), request.getSize()));
    }

    public Products getProductById(Long productId) {
        log.info("Fetching product by ID: {}", productId);
        return productRepository.findById(productId)
                .orElse(null);
    }

    public Products createProduct(Products product) {
        log.info("Creating a new product: {}", product);
        Users adminUser = userRepository.findByUsername("admin");
        if (product.getUser() == null) {
            // Если пользователь не указан, устанавливаем пользователя "admin" в качестве владельца продукта
            product.setUser(adminUser);
        }
        product.setCreation_date(LocalDateTime.now());
        return productRepository.save(product);
    }

    public Products updateProduct(Long productId, Products updatedProduct) {
        log.info("Updating product with ID: {}. New product details: {}", productId, updatedProduct);
        Products productFromDb = getProductById(productId);
        BeanUtils.copyProperties(updatedProduct, productFromDb, "id");
        return productRepository.save(productFromDb);
    }

    public void deleteProduct(Long productId) {
        log.info("Deleting product with ID: {}", productId);
        Products product = getProductById(productId);
        productRepository.delete(product);
    }

    public List<Products> findProductsByTitle(String title) {
        log.info("Fetching products by name containing: {}", title);
        return productRepository.findByTitleContaining(title);
    }
}
