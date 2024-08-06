package infnet.edu.apibloco.Controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import infnet.edu.apibloco.Constants.Messages;
import infnet.edu.apibloco.Domain.Contracts.Requests.Products.CreateProductRequest;
import infnet.edu.apibloco.Domain.Contracts.Requests.Products.UpdateProductRequest;
import infnet.edu.apibloco.Domain.Contracts.Responses.ErrorResponse;
import infnet.edu.apibloco.Domain.Models.Product;
import infnet.edu.apibloco.Infrastructure.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;

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
    public ResponseEntity<?> GetProduct(@RequestParam(name = IdParam) long id, 
    HttpServletRequest httpServletRequest) {

        var fetched = _service.findById(id);
        if(fetched.isPresent())
        {
            var result = fetched.get();
            if (result.id > 0)
            return new ResponseEntity<Product>(result, HttpStatus.OK);

        }

        
        return new ResponseEntity<ErrorResponse>(
            new ErrorResponse(HttpStatus.NOT_FOUND, 
            Messages.Not_Found , 
            httpServletRequest.getRequestURI())
            ,HttpStatus.NOT_FOUND);

    }

    @GetMapping(GetAllEndpoint)
    public ResponseEntity<List<Product>> GetProducts() {

        List<Product> result = StreamSupport.stream(_service.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return new ResponseEntity<List<Product>>(result, HttpStatus.OK);
    }

    @PostMapping(CreateEndpoint)
    public ResponseEntity<?> CreateProduct(@RequestBody CreateProductRequest request) {

        var product = new Product(0, request.getName(), request.getPrice(), request.getDescription());

        var result = _service.save(product);

        return new ResponseEntity<Product>(result, HttpStatus.CREATED);

    }

    @PutMapping(UpdateEndpoint)
    public ResponseEntity<?> Updateproduct(@RequestBody UpdateProductRequest request,
     HttpServletRequest httpServletRequest) {

        var product = new Product(0, request.getName(), request.getPrice(), request.getDescription());

        var fetched = _service.findById(request.getId());
        if(fetched.isPresent())
        {
            var result = fetched.get();

            if (result.Equals(product)) {
                var item = _service.save(product);
                return new ResponseEntity<Product>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<ErrorResponse>(
            new ErrorResponse(HttpStatus.NOT_FOUND, 
            Messages.Not_Found , 
            httpServletRequest.getRequestURI())
            ,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(DeleteEndpoint)
    public ResponseEntity<?> Deleteproduct(@RequestParam(name = IdParam) long id,
     HttpServletRequest httpServletRequest) {

        var fetched = _service.findById(id);
        if(fetched.isPresent())
        {
            var result = fetched.get();
            if (result.name != "") {
                _service.delete(result);
                return new ResponseEntity<Product>(result, HttpStatus.OK);
            }
        }
        return new ResponseEntity<ErrorResponse>(
            new ErrorResponse(HttpStatus.NOT_FOUND, 
            Messages.Not_Found , 
            httpServletRequest.getRequestURI())
            ,HttpStatus.NOT_FOUND);
    }

}
