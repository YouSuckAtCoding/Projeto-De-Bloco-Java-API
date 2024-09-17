package infnet.edu.apibloco.Domain.Contracts.Requests.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateUserRequest {
    
    @NotBlank
    @NotNull
    private String id;
    @NotBlank
    @NotNull
    @Size(min = 5, max = 30)
    private String name;
    @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    @Size(min = 5, max = 30)
    private String password;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;        
    }
}
