package infnet.edu.apibloco.Controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import infnet.edu.apibloco.Domain.Contracts.Requests.Products.CreateProductRequest;
import infnet.edu.apibloco.Domain.Contracts.Requests.Products.UpdateProductRequest;
import infnet.edu.apibloco.Domain.Models.Product;
import infnet.edu.apibloco.Infrastructure.ProductRepository;

@RestController
public class ProductController {

    private static final String Base = "api/product";
    private static final String GetEndpoint = Base;
    private static final String GetAllEndpoint = Base + "/all";
    private static final String CreateEndpoint = Base;
    private static final String UpdateEndpoint = Base + "/update";
    private static final String DeleteEndpoint = Base;
    private static final String IdParam = "id";

    @Autowired
    private ProductRepository _service;

    @GetMapping(GetEndpoint)
    public ResponseEntity<?> GetProducts(@RequestParam(name = IdParam) long id) {

        Product fetched = _service.findById(id).get();

        if (fetched.id > 0)
            return new ResponseEntity<Product>(fetched, HttpStatusCode.valueOf((200)));

        return new ResponseEntity<>(HttpStatusCode.valueOf((404)));

    }

    @GetMapping(GetAllEndpoint)
    public ResponseEntity<List<Product>> GetProducts() {

        List<Product> result = StreamSupport.stream(_service.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return new ResponseEntity<List<Product>>(result, HttpStatusCode.valueOf((200)));
    }

    @PostMapping(CreateEndpoint)
    public ResponseEntity<?> CreateProduct(@RequestBody CreateProductRequest request) {

        var product = new Product(0, request.getName(), request.getPrice(), request.getDescription());

        var result = _service.save(product);

        return new ResponseEntity<Product>(result, HttpStatusCode.valueOf((201)));

    }

    @PutMapping(UpdateEndpoint)
    public ResponseEntity<?> Updateproduct(@RequestBody UpdateProductRequest request) {

        var product = new Product(0, request.getName(), request.getPrice(), request.getDescription());

        var fetched = _service.findById(request.getId()).get();

        if (fetched.Equals(product)) {
            var result = _service.save(product);

            return new ResponseEntity<Product>(result, HttpStatusCode.valueOf((200)));
        }

        return new ResponseEntity<>(HttpStatusCode.valueOf((404)));
    }

    @DeleteMapping(DeleteEndpoint)
    public ResponseEntity<?> Deleteproduct(@RequestParam(name = IdParam) long id) {
        Product fetched = _service.findById(id).get();

        if (fetched.Name != "") {
            _service.delete(fetched);
            return new ResponseEntity<Product>(fetched, HttpStatusCode.valueOf((200)));
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf((404)));
    }

}
