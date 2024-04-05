//package ru.aiteko.WebStore.service.mapper;
//
//import org.springframework.stereotype.Service;
//import ru.aiteko.WebStore.dto.ShoppingCartDto;
//import ru.aiteko.WebStore.entity.ShoppingCarts;
//
//@Service
//public class ShoppingCartMapper {
//    public ShoppingCartDto toDto(ShoppingCarts shoppingCart){
//        return ShoppingCartDto.builder()
//                .id(shoppingCart.getId())
//                .products(shoppingCart.getProducts())
//                .user(shoppingCart.getUser())
//                .build();
//    }
//
//    public ShoppingCarts addProduct(ShoppingCartDto shoppingCartDto, prod){
//
//        shoppingCart.setProducts(shoppingCartDto.getProducts());
//
//        return shoppingCart;
//    }
//}
