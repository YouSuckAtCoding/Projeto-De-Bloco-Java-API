package infnet.edu.apibloco.Domain.Events;

import infnet.edu.apibloco.Domain.Primitives.Event;

public class CreateProductEvent extends Event<String>{

    public String name;  
    public double price;
    public String description;
    
    public CreateProductEvent(String id, String name, double price, String description) {
        super(id);
        this.name = name;
        this.price = price;
        this.description = description;
    }


}
