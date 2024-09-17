package infnet.edu.apibloco.Domain.Contracts.Requests.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginRequest {

     @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String pass;

public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public String getPass() {
    return pass;
}
public void setPass(String pass) {
    this.pass = pass;
}

}
