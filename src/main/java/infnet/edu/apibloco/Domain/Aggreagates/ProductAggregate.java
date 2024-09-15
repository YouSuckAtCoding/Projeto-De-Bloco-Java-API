package infnet.edu.apibloco.Domain.Aggreagates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import infnet.edu.apibloco.Commands.Product.CreateProductCommand;
import infnet.edu.apibloco.Domain.Events.CreateProductEvent;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    public String id;
    public String name;  
    public double price;
    public String description;
    
    public ProductAggregate()
    {}

    public ProductAggregate(String id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand command)
    {
        AggregateLifecycle.apply(new CreateProductEvent(command.id,
        command.name, command.price, command.description));
    }

    @EventSourcingHandler
    protected void on(CreateProductEvent event)
    {
        this.id = event.id;
        this.name = event.name;
        this.price = event.price;
        this.description = event.description;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
