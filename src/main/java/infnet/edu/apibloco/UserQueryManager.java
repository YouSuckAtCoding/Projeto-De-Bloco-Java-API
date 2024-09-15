package infnet.edu.apibloco;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import infnet.edu.apibloco.Domain.Aggreagates.UserAggregate;
import infnet.edu.apibloco.Domain.Events.CreateUserEvent;
import infnet.edu.apibloco.Domain.Models.User;
import infnet.edu.apibloco.Domain.Primitives.Event;
import infnet.edu.apibloco.Infrastructure.UserRepository;

@Component
public class UserQueryManager {

    @Autowired
    private UserRepository _repository;

    @Autowired
    @Qualifier("userAggregateEventSourcingRepository")
    private EventSourcingRepository<UserAggregate> userAggregateEventSourcingRepository;


    @EventHandler
    void on(CreateUserEvent event) {
        persistUser(buildQueryUser(getUserFromEvent(event)));
    }

    private UserAggregate getUserFromEvent(Event<String> event) {
        return userAggregateEventSourcingRepository.load(event.id.toString()).getWrappedAggregate().getAggregateRoot();
    }

    private User findExistingOrCreateQueryUser(String id) {
        return _repository.findById(id).isPresent() ? _repository.findById(id).get() : new User();
    }

    private User buildQueryUser(UserAggregate UserAggregate) {
        User user = findExistingOrCreateQueryUser(UserAggregate.getId());

        user.id = (UserAggregate.getId());
        user.Name = (UserAggregate.getName());
        user.Email = (UserAggregate.getEmail());
        user.Password = (UserAggregate.getPassword());

        return user;
    }

    private void persistUser(User User) {
        _repository.save(User);
    }

}
