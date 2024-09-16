package infnet.edu.apibloco.Commands.Post;

import infnet.edu.apibloco.Domain.Models.User;
import infnet.edu.apibloco.Domain.Primitives.Command;

public class CreatePostCommand extends Command<String>{

    public User user;
    public String description;

    public CreatePostCommand(String id, User user, String description) {
        super(id);
        this.user = user;
        this.description = description;
    }
    


}
