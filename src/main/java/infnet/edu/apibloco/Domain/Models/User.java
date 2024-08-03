package infnet.edu.apibloco.Domain.Models;

import jakarta.persistence.*;

import infnet.edu.apibloco.Domain.Primitives.EntityRoot;

@Entity
@Table(name = "Users")
public class User extends EntityRoot {

    @Column(nullable = false, length = 30)
    public String Name;
    @Column(nullable = false, length = 35)
    public String Email;
    @Column(nullable = false, length = 20)
    public String Password;

    public User(long id, String name, String email, String password) {
        super(id);
        this.Name = name;
        this.Email = email;
        this.Password = password;
    }

    public User()
    {}

}
