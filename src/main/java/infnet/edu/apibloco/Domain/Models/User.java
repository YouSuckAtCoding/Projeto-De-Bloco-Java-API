package infnet.edu.apibloco.Domain.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import infnet.edu.apibloco.Domain.Primitives.EntityRoot;

@Entity
@Table(name = "Users")
public class User extends EntityRoot{

   

    @NotNull(message = "Name can't be empty.")
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    public String Name;
    @NotNull(message = "Email can't be empty.")
    @Size(max = 30)
    @Email
    @Column(nullable = false, length = 35)
    public String Email;
    @NotNull(message = "Please create a password.")
    @Size(max = 30)
    @Column(nullable = false, length = 20)    
    public String Password;

    public User(long id, String name, String email, String password) {
        super(id);
        this.Name = name;
        this.Email = email;
        this.Password = password;
    }

    public User() {
    }


    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append("Name: " + this.Name + "\n");
        str.append("Email: " + this.Email + "\n");
        return str.toString();
    }
}
