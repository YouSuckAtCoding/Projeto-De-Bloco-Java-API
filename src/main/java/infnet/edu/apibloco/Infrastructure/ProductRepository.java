package infnet.edu.apibloco.Infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import infnet.edu.apibloco.Domain.Models.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

}
