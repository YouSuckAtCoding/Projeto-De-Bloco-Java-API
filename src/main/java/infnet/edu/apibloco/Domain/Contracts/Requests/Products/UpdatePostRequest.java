package infnet.edu.apibloco.Domain.Contracts.Requests.Products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdatePostRequest {
    
    private String id;
    @NotNull
    @NotBlank        
    private String userId;
    @NotNull
	@Size(min=5, max=100)
    private String description;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
   
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    
}
