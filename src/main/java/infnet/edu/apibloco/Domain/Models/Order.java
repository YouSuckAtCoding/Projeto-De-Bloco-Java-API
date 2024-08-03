package infnet.edu.apibloco.Domain.Models;

import infnet.edu.apibloco.Domain.Primitives.AggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order extends AggregateRoot{
    
    @ManyToOne
    public User Buyer;
    @ManyToOne
    public Product product;

    public Order(long id, User buyer, Product product) {
        super(id);

        this.Buyer = buyer;
        this.product = product;

    }
    
    public Order()
    {}
    

}
