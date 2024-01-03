package ru.aiteko.WebStore.entity;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.aiteko.WebStore.entity.views.UserView;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "users")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "User entity")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name ="user_id"))
    @Enumerated(EnumType.STRING)
    @Schema(hidden = true)
    private Set<Role> role;

    @OneToMany(mappedBy = "user")
    @Schema(hidden = true)
    private Set<ShoppingCarts> shoppingCarts = new HashSet<>();
}