package infnet.edu.apibloco.Domain.Models;

import infnet.edu.apibloco.Domain.Primitives.AggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Order_Item")
public class OrderItem extends AggregateRoot{

    @ManyToOne
    public Order order;
    @OneToOne
    public Product product;
    @Column(nullable = false, name= "price")
    public double price;
    @Column(nullable = false, name= "quantity")
    public int quantity;
    public OrderItem(String id, Order order, Product product, double price, int quantity) {
        super(id);
        this.order = order;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }
    public OrderItem()
    {}   

}
