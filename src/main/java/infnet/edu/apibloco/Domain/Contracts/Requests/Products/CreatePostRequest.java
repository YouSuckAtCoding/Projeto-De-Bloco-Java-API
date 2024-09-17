package infnet.edu.apibloco.Domain.Contracts.Requests.Products;


public class CreatePostRequest {

    public String userId;
    public String description;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
   
    
}
