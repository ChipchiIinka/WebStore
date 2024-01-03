package ru.aiteko.WebStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aiteko.WebStore.entity.NewsAndPromotions;

@Repository
public interface NewsRepository extends JpaRepository<NewsAndPromotions, Long> {

}
