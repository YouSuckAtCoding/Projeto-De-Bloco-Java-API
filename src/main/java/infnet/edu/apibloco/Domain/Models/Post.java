package infnet.edu.apibloco.Domain.Models;

import infnet.edu.apibloco.Domain.Primitives.EntityRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Post extends EntityRoot
{    
    @ManyToOne
    public User user;
    @Column(nullable = false, length = 100)
    public String description;
        
    public Post(String id, User user, String description) {
        super(id);
        this.user = user;
        this.description = description;
    }

    public Post()
    {}


    


}
