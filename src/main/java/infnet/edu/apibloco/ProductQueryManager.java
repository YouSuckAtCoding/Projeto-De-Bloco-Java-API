package infnet.edu.apibloco;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import infnet.edu.apibloco.Domain.Aggreagates.ProductAggregate;
import infnet.edu.apibloco.Domain.Events.CreateProductEvent;
import infnet.edu.apibloco.Domain.Models.Product;
import infnet.edu.apibloco.Domain.Primitives.Event;
import infnet.edu.apibloco.Infrastructure.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;

@Component
public class ProductQueryManager {

    @Autowired
    private ProductRepository _repository;

    @Autowired
    @Qualifier("productAggregateEventSourcingRepository")
    private EventSourcingRepository<ProductAggregate> productAggregateEventSourcingRepository;

      @EventHandler
    void on(CreateProductEvent event) {
        persistProduct(buildQueryProduct(getProductFromEvent(event)));
    }

    private ProductAggregate getProductFromEvent(Event<String> event) {
        return productAggregateEventSourcingRepository.load(event.id.toString()).getWrappedAggregate().getAggregateRoot();
    }

    private Product findExistingOrCreateQueryProduct(String id) {
        return _repository.findById(id).isPresent() ? _repository.findById(id).get() : new Product();
    }

    private Product buildQueryProduct(ProductAggregate productAggregate) {
        Product product = findExistingOrCreateQueryProduct(productAggregate.getId());

        product.id = (productAggregate.getId());
        product.name = (productAggregate.getName());
        product.description = (productAggregate.getDescription());
        product.price = (productAggregate.getPrice());

        return product;
    }

    private void persistProduct(Product Product) {
        _repository.save(Product);
    }
}   

