package infnet.edu.apibloco.Commands;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.concurrent.CompletableFuture;

import infnet.edu.apibloco.Domain.Aggreagates.UserAggregate;

@Service
public class UserCommandService implements IUserCommandService {
    
    @Autowired
    private CommandGateway _gateway;

    @Override
    public CompletableFuture<String> CreatePedido(UserAggregate user)
    {
        return _gateway.send(new CreateUserCommand(
            UUID.randomUUID().toString(), 
            user.getName(), 
            user.getEmail(), 
            user.getPassword()));
    }
}
