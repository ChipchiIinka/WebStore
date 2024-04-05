package ru.aiteko.WebStore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.aiteko.WebStore.dto.ProductDto;
import ru.aiteko.WebStore.entity.Products;
import ru.aiteko.WebStore.exception.AitekoException;
import ru.aiteko.WebStore.exception.ErrorType;
import ru.aiteko.WebStore.repository.ProductRepository;
import ru.aiteko.WebStore.service.mapper.ProductMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getAllProducts() {
        log.info("Fetching all products");

        return productMapper.toListDto(productRepository.findAll());
    }

    public List<ProductDto> findProductsByTitle(String title) {
        log.info("Fetching products by name containing: {}", title);

        return productMapper.toListDto(productRepository.findByTitleContaining(title));
    }

    public ProductDto getProductById(Long productId) {
        log.info("Fetching product by ID: {}", productId);

        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new AitekoException(ErrorType.NOT_FOUND, "Продукт c ID " + productId + " не найден"));
        return productMapper.toDto(product);
    }

    public void createProduct(ProductDto productDto) {
        log.info("Creating a new product: {}", productDto);

        Products product = productMapper.toEntity(productDto);
        productRepository.save(product);
    }

    public void updateProduct(Long productId, ProductDto updatedProduct) {
        log.info("Updating product with ID: {}. New product details: {}", productId, updatedProduct);

        Products productFromDb = productRepository.findById(productId)
                .orElseThrow(() -> new AitekoException(ErrorType.NOT_FOUND, "Продукт c ID " + productId + " не найден"));

        productFromDb.setTitle(updatedProduct.getTitle());
        productFromDb.setDescription(updatedProduct.getDescription());
        productFromDb.setPrice(updatedProduct.getPrice());

        productRepository.save(productFromDb);
    }

    public void deleteProduct(Long productId) {
        log.info("Deleting product with ID: {}", productId);

        productRepository.deleteById(productId);
    }
}
