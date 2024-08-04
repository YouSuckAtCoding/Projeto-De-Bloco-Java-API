package infnet.edu.apibloco.Domain.Models;

import java.time.LocalDateTime;


import infnet.edu.apibloco.Domain.Primitives.AggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order extends AggregateRoot{
    
    @ManyToOne
    public User user;
    @Column(name = "order_Date")
    public LocalDateTime order_Date;
    @Column(name = "total")
    public double total;
    
    public Order(long id, User buyer, LocalDateTime order_Date, double total) {
        super(id);
        this.user = buyer;
        this.order_Date = order_Date;
        this.total = total;
    }

    public Order()
    {}

    

    




    
    

}
