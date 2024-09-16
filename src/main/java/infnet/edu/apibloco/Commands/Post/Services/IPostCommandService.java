package infnet.edu.apibloco.Commands.Post.Services;

import java.util.concurrent.CompletableFuture;

import infnet.edu.apibloco.Domain.Aggreagates.PostAggregate;

public interface IPostCommandService {

    CompletableFuture<String> CreatePost(PostAggregate product);

}