package infnet.edu.apibloco.Domain.Contracts.Requests.Products;

public class CreateProductRequest {

    public String name;
    public double price;
    public String description;
   
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}
