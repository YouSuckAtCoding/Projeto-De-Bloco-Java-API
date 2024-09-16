package infnet.edu.apibloco.Domain.Contracts.Requests.Products;

import infnet.edu.apibloco.Domain.Models.User;

public class UpdatePostRequest {
    
    public String id;        
    public User user;
    public String description;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
    
}
