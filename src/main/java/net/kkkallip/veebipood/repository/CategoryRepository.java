package net.kkkallip.veebipood.repository;

import net.kkkallip.veebipood.entity.Category;
import net.kkkallip.veebipood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
