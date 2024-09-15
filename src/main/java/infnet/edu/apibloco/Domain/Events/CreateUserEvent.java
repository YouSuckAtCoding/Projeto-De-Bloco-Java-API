package infnet.edu.apibloco.Domain.Events;

import infnet.edu.apibloco.Domain.Primitives.Event;

public class CreateUserEvent extends Event<String>{

    public String name;
    public String email;
    public String password;
    
    public CreateUserEvent(String id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
