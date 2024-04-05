//package ru.aiteko.WebStore.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "shopping_cart_products")
//@NoArgsConstructor
//public class ShoppingCartProducts {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private long id;
//
//    @ManyToOne
//    @JoinColumn(name = "shopping_cart_id")
//    private ShoppingCarts shoppingCart;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Products product;
//}
