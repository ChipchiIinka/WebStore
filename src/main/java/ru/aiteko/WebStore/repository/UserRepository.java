package ru.aiteko.WebStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aiteko.WebStore.entity.Role;
import ru.aiteko.WebStore.entity.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    boolean existsByUsername(String admin);
}
