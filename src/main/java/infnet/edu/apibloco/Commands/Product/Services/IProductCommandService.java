package infnet.edu.apibloco.Commands.Product.Services;

import java.util.concurrent.CompletableFuture;

import infnet.edu.apibloco.Domain.Aggreagates.ProductAggregate;

public interface IProductCommandService {

    CompletableFuture<String> CreateProduct(ProductAggregate product);

}