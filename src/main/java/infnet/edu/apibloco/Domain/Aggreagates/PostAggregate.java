package infnet.edu.apibloco.Domain.Aggreagates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import infnet.edu.apibloco.Commands.Post.CreatePostCommand;
import infnet.edu.apibloco.Domain.Events.CreatePostEvent;
import infnet.edu.apibloco.Domain.Models.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Aggregate
public class PostAggregate {

    @AggregateIdentifier
    public String id;
    @ManyToOne
    @JoinColumn(name = "userId")
    public User user;
    public String description;
    
    public PostAggregate(String id, User user, String description) {
        this.id = id;
        this.user = user;
        this.description = description;
    }


    public PostAggregate()
    {}

  
    @CommandHandler
    public PostAggregate(CreatePostCommand command)
    {
        AggregateLifecycle.apply(new CreatePostEvent(command.id,
        command.user, command.description));
    }

    @EventSourcingHandler
    protected void on(CreatePostEvent event)
    {
        this.id = event.id;
        this.user = event.user;
        this.description = event.description;

    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }




}
