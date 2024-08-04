package infnet.edu.apibloco.Infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import infnet.edu.apibloco.Domain.Models.Order;
import jakarta.transaction.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

    @Query("select o from Order o where o.user = :userId")
    List<Order> findOrdersByUser(@Param("userId") long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Order o\r\n" + //
                "JOIN (\r\n" + //
                "    SELECT oi.order.id as ord, SUM(oi.price * oi.quantity) AS total_price\r\n" + //
                "    FROM OrderItem oi\r\n" + //
                "    GROUP BY ord\r\n" + //
                ") oi ON o.id = oi.ord\r\n" + //
                "SET o.total = oi.total_price\r\n" + //
                "WHERE o.id = :orderId")
    void updateOrderTotal(@Param("orderId") long orderId );
    
}
