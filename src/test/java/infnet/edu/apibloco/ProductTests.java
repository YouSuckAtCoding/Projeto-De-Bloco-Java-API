package infnet.edu.apibloco;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import infnet.edu.apibloco.Domain.Models.Product;
import infnet.edu.apibloco.Infrastructure.ProductRepository;

@SpringBootTest
public class ProductTests {

    private static final String ProductDescription = "Produto teste";
    private static final double ProductPrice = 50.0;
    private static final String ProductName = "Product";
    @Autowired
    private ProductRepository _productRepository;

    @Test
	void Should_Return_Products_From_ProductRepository()
	{
		var result = _productRepository.findAll();
		Assert.notEmpty(result, "Products retrieved");
	}

    @Test
    void Should_Create_Product_In_ProductRepository()
    {
        var product = new Product(0, ProductName, ProductPrice, ProductDescription);

        var created = _productRepository.save(product);
        var result = _productRepository.findById(created.id).get();

        Assert.notNull(result, "User Created"); 
    }

    @Test
    void Should_Delete_Product_From_ProductRepository()
    {
        var product = new Product(0, ProductName, 50.0, ProductDescription);

        var created = _productRepository.save(product);
        _productRepository.delete(created);
        var result = _productRepository.findById(created.id);

        Assert.isTrue(result.isPresent() == false, "User Deleted");

    }

    @Test
    void Should_Update_Product_In_ProductRepository()
    {
        String newName = "Product123";
        var product = new Product(0, ProductName, 50.0, ProductDescription);
        
        var created = _productRepository.save(product);
        created.name = newName;
        var result = _productRepository.save(created);

        Assert.isTrue(!(result.name != newName), "User Updated");
    }
}
