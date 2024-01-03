package ru.aiteko.WebStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shopping_carts")
@Getter
@Setter
@NoArgsConstructor
public class ShoppingCarts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToMany
    @JoinTable(
            name = "shopping_cart_products",
            joinColumns = @JoinColumn(name = "shopping_cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Products> products = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}

