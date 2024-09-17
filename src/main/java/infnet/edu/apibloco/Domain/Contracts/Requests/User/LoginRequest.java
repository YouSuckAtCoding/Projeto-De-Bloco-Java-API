package infnet.edu.apibloco.Domain.Contracts.Requests.User;

public class LoginRequest {

    private String email;
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
