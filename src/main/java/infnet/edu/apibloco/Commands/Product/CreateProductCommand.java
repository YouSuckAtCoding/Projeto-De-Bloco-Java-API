package infnet.edu.apibloco.Commands.Product;

import infnet.edu.apibloco.Domain.Primitives.Command;

public class CreateProductCommand extends Command<String>{

    public String name;  
    public double price;
    public String description;

    public CreateProductCommand(String id, String name, double price, String description) {
        super(id);
        this.name = name;
        this.price = price;
        this.description = description;
    }
    


}
