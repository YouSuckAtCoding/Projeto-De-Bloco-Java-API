package infnet.edu.apibloco;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import infnet.edu.apibloco.Domain.Aggreagates.PostAggregate;
import infnet.edu.apibloco.Domain.Events.CreatePostEvent;
import infnet.edu.apibloco.Domain.Models.*;
import infnet.edu.apibloco.Domain.Primitives.Event;
import infnet.edu.apibloco.Infrastructure.PostRepository;
import infnet.edu.apibloco.Infrastructure.UserRepository;

import org.springframework.beans.factory.annotation.Qualifier;

@Component
public class PostQueryManager {

    @Autowired
    private PostRepository _repository;

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    @Qualifier("postAggregateEventSourcingRepository")
    private EventSourcingRepository<PostAggregate> postAggregateEventSourcingRepository;

    @EventHandler
    void on(CreatePostEvent event) {
        persistpost(buildQuerypost(getpostFromEvent(event)));
    }

    private PostAggregate getpostFromEvent(Event<String> event) {
        return postAggregateEventSourcingRepository.load(event.id.toString()).getWrappedAggregate().getAggregateRoot();
    }

    private Post findExistingOrCreateQuerypost(String id) {
        return _repository.findById(id).isPresent() ? _repository.findById(id).get() : new Post();
    }

    private Post buildQuerypost(PostAggregate postAggregate) {
        Post post = findExistingOrCreateQuerypost(postAggregate.getId());

        post.id = (postAggregate.getId());
        post.user = (postAggregate.getUser());
        post.description = (postAggregate.getDescription());

        return post;

    }

    private void persistpost(Post post) {
        User user = _userRepository.findById(post.user.id).get();

        if (user.id == post.user.id) {
            _repository.save(post);
        }

    }
}
