package infnet.edu.apibloco.Domain.Contracts.Requests.Products;

import infnet.edu.apibloco.Domain.Models.User;

public class CreatePostRequest {

    public User user;
    public String description;
    
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
