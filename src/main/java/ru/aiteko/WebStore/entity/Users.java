package ru.aiteko.WebStore.entity;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.aiteko.WebStore.dto.views.UserView;

import java.util.Set;

@Entity
@Table (name = "users")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "User entity")
public class Users{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(hidden = true)
    private long id;

    @Column(name = "username", nullable = false, unique = true, length = 128)
    @JsonView(UserView.ShortInfo.class)
    @Schema(description = "User username", example = "Example username")
    private String username;

    @Column(name = "pass", nullable = false, length = 128)
    @Schema(description = "User password", example = "Example password")
    private String password;

    @Column(name = "email", nullable = false, unique = true, length = 128)
    @JsonView(UserView.ShortInfo.class)
    @Schema(description = "User email", example = "Example email@example.com")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

//    @OneToMany(mappedBy = "user")
//    @Schema(hidden = true)
//    private Set<ShoppingCarts> shoppingCarts = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "shopping_cart",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "products")
    )
    private Set<Products> shoppingCart;


}