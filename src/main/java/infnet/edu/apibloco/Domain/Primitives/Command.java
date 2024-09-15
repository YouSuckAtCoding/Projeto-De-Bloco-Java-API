package infnet.edu.apibloco.Domain.Primitives;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class Command<T> {

    @TargetAggregateIdentifier
    public final T id;

    public Command(T id)
    {
        this.id = id;
    } 

}
