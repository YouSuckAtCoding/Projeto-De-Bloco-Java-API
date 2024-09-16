package infnet.edu.apibloco.Config;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import infnet.edu.apibloco.Domain.Aggreagates.PostAggregate;
import infnet.edu.apibloco.Domain.Aggreagates.UserAggregate;

@Configuration
public class AxonConfig {

    @Bean
    EventSourcingRepository<UserAggregate> userAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<UserAggregate> repository = EventSourcingRepository.builder(UserAggregate.class).eventStore(eventStore).build();
        return repository;
    }

    @Bean
    EventSourcingRepository<PostAggregate> postAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<PostAggregate> repository = EventSourcingRepository.builder(PostAggregate.class).eventStore(eventStore).build();
        return repository;
    }
}