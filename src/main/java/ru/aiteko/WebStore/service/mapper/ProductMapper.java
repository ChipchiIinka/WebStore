package ru.aiteko.WebStore.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.aiteko.WebStore.authfacade.IAuthenticationFacade;
import ru.aiteko.WebStore.dto.ProductDto;
import ru.aiteko.WebStore.entity.Products;
import ru.aiteko.WebStore.entity.Users;
import ru.aiteko.WebStore.exception.AitekoException;
import ru.aiteko.WebStore.exception.ErrorType;
import ru.aiteko.WebStore.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductMapper {
    private final IAuthenticationFacade authenticationFacade;
    private final UserRepository userRepo;

    public List<ProductDto> toListDto(List<Products> product) {
        return product.stream().map(this::toDto).toList();
    }

    public ProductDto toDto(Products product){
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .creation_date(product.getCreation_date())
                .price(product.getPrice())
                .user(product.getUser())
                .build();
    }

    public Products toEntity(ProductDto productDto){
        Products product = new Products();
        Authentication authentication = authenticationFacade.getAuthentication();
        Users user = userRepo.findByUsername(authentication.getName())
                        .orElseThrow(() -> new AitekoException(ErrorType.NOT_FOUND,
                                "Пожалуйста попробуйте снова или авторизируйтесь заново" ));

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setCreation_date(LocalDateTime.now());
        product.setPrice(productDto.getPrice());
        product.setUser(user);

        return product;
    }
}
