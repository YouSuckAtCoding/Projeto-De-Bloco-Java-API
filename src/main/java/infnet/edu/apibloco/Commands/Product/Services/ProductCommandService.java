package infnet.edu.apibloco.Commands.Product.Services;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import infnet.edu.apibloco.Commands.Product.CreateProductCommand;
import infnet.edu.apibloco.Domain.Aggreagates.ProductAggregate;

@Service
public class ProductCommandService implements IProductCommandService {

    @Autowired
    private CommandGateway _gateway;

    @Override
    public CompletableFuture<String> CreateProduct(ProductAggregate product)
    {
        return _gateway.send(new CreateProductCommand(
            UUID.randomUUID().toString(), 
            product.name,
            product.price,
            product.description));
    }

}
