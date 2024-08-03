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
    public String Name;
    @Column(nullable = false)
    public double Price;
    @Column(nullable = false, length = 100)
    public String Description;
    
    public Product(long id, String name, double price, String description) {
        super(id);
        Name = name;
        Price = price;
        Description = description;
    }
    
    public Product()
    {}


    


}
