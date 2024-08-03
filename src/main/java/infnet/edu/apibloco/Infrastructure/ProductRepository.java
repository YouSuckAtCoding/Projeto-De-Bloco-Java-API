package infnet.edu.apibloco.Infrastructure;

import org.springframework.data.repository.CrudRepository;

import infnet.edu.apibloco.Domain.Models.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
