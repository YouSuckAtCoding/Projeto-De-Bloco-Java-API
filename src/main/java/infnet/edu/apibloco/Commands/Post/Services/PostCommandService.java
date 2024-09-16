package infnet.edu.apibloco.Commands.Post.Services;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import infnet.edu.apibloco.Commands.Post.CreatePostCommand;
import infnet.edu.apibloco.Domain.Aggreagates.PostAggregate;

@Service
public class PostCommandService implements IPostCommandService {

    @Autowired
    private CommandGateway _gateway;

    @Override
    public CompletableFuture<String> CreatePost(PostAggregate post)
    {
        return _gateway.send(new CreatePostCommand(
            UUID.randomUUID().toString(), 
            post.user,
            post.description));
    }

}
