package ru.aiteko.WebStore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.aiteko.WebStore.authfacade.IAuthenticationFacade;
import ru.aiteko.WebStore.dto.UserDto;
import ru.aiteko.WebStore.entity.Products;
import ru.aiteko.WebStore.entity.Users;
import ru.aiteko.WebStore.exception.AitekoException;
import ru.aiteko.WebStore.exception.ErrorType;
import ru.aiteko.WebStore.repository.ProductRepository;
import ru.aiteko.WebStore.repository.UserRepository;
import ru.aiteko.WebStore.service.mapper.UserMapper;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
private final UserRepository userRepo;
private final ProductRepository productRepo;
private final UserMapper userMapper;
private final IAuthenticationFacade authenticationFacade;

    public UserDto getUserById(Long id) {
        log.info("Fetching product by ID: {}", id);

        Users user = userRepo.findById(id)
                .orElseThrow(() -> new AitekoException(ErrorType.NOT_FOUND,
                        "Пользователь c ID " + id + " не найден"));
        return userMapper.toDto(user);
    }

    public void addToShoppingCart(long productId){
        log.info("Adding to shopping cart by authenticated user and by productID: {}", productId);

        Authentication authentication = authenticationFacade.getAuthentication();
        Users user = userRepo.findByUsername(authentication.getName())
                .orElseThrow(()-> new AitekoException(ErrorType.NOT_FOUND,
                        "Пользователь " + authentication.getName() + " не найден, попробуйте снова или авторизируйтесь заново"));
        Products product = productRepo.findById(productId)
                .orElseThrow(()-> new AitekoException(ErrorType.NOT_FOUND,
                        "Товар c ID " + productId + " не найден"));

        user.getShoppingCart().add(product);
    }

    public Set<Products> getUsersShoppingCartProduct(){
        log.info("Fetching shopping cart by authenticated user");

        Authentication authentication = authenticationFacade.getAuthentication();
        Users user = userRepo.findByUsername(authentication.getName())
                .orElseThrow(()-> new AitekoException(ErrorType.NOT_FOUND,
                        "Пожалуйста попробуйте снова или авторизируйтесь заново"));
        if (!user.getShoppingCart().isEmpty()){
            return user.getShoppingCart();
        } else {
            return new HashSet<>();
        }
    }
}
