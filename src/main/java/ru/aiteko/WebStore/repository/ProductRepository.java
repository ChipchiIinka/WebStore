package ru.aiteko.WebStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aiteko.WebStore.entity.Products;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findByTitleContaining (String title);
}
