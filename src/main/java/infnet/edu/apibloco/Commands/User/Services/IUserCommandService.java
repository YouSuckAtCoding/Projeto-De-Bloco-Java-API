package infnet.edu.apibloco.Commands.User.Services;

import java.util.concurrent.CompletableFuture;

import infnet.edu.apibloco.Domain.Aggreagates.UserAggregate;

public interface IUserCommandService {

    CompletableFuture<String> CreateUser(UserAggregate user);



}