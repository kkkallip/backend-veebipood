package net.kkkallip.veebipood.repository;

import net.kkkallip.veebipood.entity.Category;
import net.kkkallip.veebipood.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
