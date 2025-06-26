package io.github.avew.citus.repository;

import io.github.avew.citus.domain.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
    @Query("SELECT o, p FROM UserOrder o JOIN UserProfile p ON o.userId = p.userId")
    List<Object[]> findOrdersWithUser();
}