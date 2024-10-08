package infnet.edu.apibloco.Domain.Aggreagates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import infnet.edu.apibloco.Commands.User.CreateUserCommand;
import infnet.edu.apibloco.Domain.Events.CreateUserEvent;

@Aggregate
public class UserAggregate {

    @AggregateIdentifier
    public String id;
    public String name;
    public String email;
    public String password;
    

    public UserAggregate()
    {}

    public UserAggregate(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @CommandHandler
    public UserAggregate(CreateUserCommand command)
    {
        AggregateLifecycle.apply(
            new CreateUserEvent(command.id, command.name, command.email, command.password)
        );
    }

    @EventSourcingHandler
    protected void on(CreateUserEvent event)
    {
        this.id = event.id;
        this.name = event.name;
        this.email = event.email;
        this.password = event.password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
