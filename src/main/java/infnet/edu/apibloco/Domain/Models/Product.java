package infnet.edu.apibloco.Domain.Models;

import infnet.edu.apibloco.Domain.Primitives.EntityRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Products")
public class Product extends EntityRoot
{
    @Column(nullable = false, length = 30)
    public String name;
    @Column(nullable = false)
    public double price;
    @Column(nullable = false, length = 100)
    public String description;
    
    public Product(String id, String name, double price, String description) {
        super(id);
        this.name = name;
        this.price = price;
        this.description = description;
    }
    
    public Product()
    {}


    


}
