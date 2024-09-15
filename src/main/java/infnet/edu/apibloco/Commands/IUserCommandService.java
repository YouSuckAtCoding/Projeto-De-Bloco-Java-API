package infnet.edu.apibloco.Commands;

import java.util.concurrent.CompletableFuture;

import infnet.edu.apibloco.Domain.Aggreagates.UserAggregate;

public interface IUserCommandService {

    CompletableFuture<String> CreatePedido(UserAggregate user);

}