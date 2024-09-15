package infnet.edu.apibloco.Infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import infnet.edu.apibloco.Domain.Models.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String>{

    @Query("select o from OrderItem o where o.order = :orderId")
    List<OrderItem> findOrderItemsByOrder(@Param("orderId") String orderId);
}
