package infnet.edu.apibloco.Domain.Models;

import jakarta.persistence.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

import infnet.edu.apibloco.Domain.Primitives.EntityRoot;

@Entity
@Table(name = "Users")
public class User extends EntityRoot {

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

    public Set<ConstraintViolation<User>> Validate(User user)
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();  
        Set<ConstraintViolation<User>> violations = validator.validate(user);     

        return violations;
    }

    public User()
    {}

}
