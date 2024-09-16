package infnet.edu.apibloco.Domain.Events;

import infnet.edu.apibloco.Domain.Models.User;
import infnet.edu.apibloco.Domain.Primitives.Event;

public class CreatePostEvent extends Event<String>{

    public User user;
    public String description;
    
    public CreatePostEvent(String id, User user, String description) {
        super(id);
        this.user = user;
        this.description = description;
    }


}
