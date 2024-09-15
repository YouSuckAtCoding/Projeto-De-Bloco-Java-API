package infnet.edu.apibloco.Config;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import infnet.edu.apibloco.Domain.Aggreagates.ProductAggregate;
import infnet.edu.apibloco.Domain.Aggreagates.UserAggregate;

@Configuration
public class AxonConfig {

    @Bean
    EventSourcingRepository<UserAggregate> userAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<UserAggregate> repository = EventSourcingRepository.builder(UserAggregate.class).eventStore(eventStore).build();
        return repository;
    }

    @Bean
    EventSourcingRepository<ProductAggregate> productAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<ProductAggregate> repository = EventSourcingRepository.builder(ProductAggregate.class).eventStore(eventStore).build();
        return repository;
    }
}