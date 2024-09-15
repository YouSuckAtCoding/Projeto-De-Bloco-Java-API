package infnet.edu.apibloco.Queries;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService implements IUserQueryService {

    private final EventStore eventStore;

    public UserQueryService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public List<Object> listEvents(String id) {
        return eventStore.readEvents(id).asStream().map( s -> s.getPayload()).collect(Collectors.toList());
    }

}
