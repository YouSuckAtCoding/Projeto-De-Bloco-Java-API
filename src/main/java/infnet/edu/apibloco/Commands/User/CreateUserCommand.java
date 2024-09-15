package infnet.edu.apibloco.Commands.User;

import infnet.edu.apibloco.Domain.Primitives.Command;

public class CreateUserCommand extends Command<String>{
    
    public String name;
    public String email;
    public String password;
    
    public CreateUserCommand(String id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
