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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import infnet.edu.apibloco.Constants.Messages;
import infnet.edu.apibloco.Domain.Contracts.Requests.Orders.CreateOrderRequest;
import infnet.edu.apibloco.Domain.Contracts.Responses.ErrorResponse;
import infnet.edu.apibloco.Domain.Models.Order;
import infnet.edu.apibloco.Infrastructure.OrderRepository;
import infnet.edu.apibloco.Infrastructure.UserRepository;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class OrderController {

    private static final String Base = "api/order";
    private static final String GetEndpoint = Base;
    private static final String GetAllEndpoint = Base + "/all";
    private static final String CreateEndpoint = Base;
    private static final String DeleteEndpoint = Base;
    private static final String IdParam = "id";
    private static final String UserIdParam = "userId";

    @Autowired
    private OrderRepository _service;
    @Autowired
    private UserRepository _userService;

    @GetMapping(GetEndpoint)
    public ResponseEntity<?> GetOrder(@RequestParam(name = IdParam) long id, HttpServletRequest httpServletRequest) {

        var fetched = _service.findById(id);

        if(fetched.isPresent())
        {
            var result = fetched.get();
            if (result.id > 0)
                return new ResponseEntity<Order>(result, HttpStatus.OK);
        }
        

            return new ResponseEntity<ErrorResponse>(
            new ErrorResponse(HttpStatus.NOT_FOUND, 
            Messages.Not_Found , 
            httpServletRequest.getRequestURI())
            ,HttpStatus.NOT_FOUND);

    }

    @GetMapping(GetAllEndpoint)
    public ResponseEntity<?> GetOrders(@RequestParam(name = UserIdParam) long id, HttpServletRequest httpServletRequest) {

        var fetched = _userService.findById(id);
        
        if(fetched.isPresent())
        {
            var result = fetched.get();
            if (result.id > 0) {
                List<Order> items = StreamSupport.stream(_service.findOrdersByUser(id).spliterator(), false)
                        .collect(Collectors.toList());
    
                return new ResponseEntity<List<Order>>(items, HttpStatus.OK);
            }
        }
        
        return new ResponseEntity<ErrorResponse>(
            new ErrorResponse(HttpStatus.NOT_FOUND, 
            Messages.Not_Found , 
            httpServletRequest.getRequestURI())
            ,HttpStatus.NOT_FOUND);
    }

    @PostMapping(CreateEndpoint)
    public ResponseEntity<?> CreateOrder(@RequestBody CreateOrderRequest request, HttpServletRequest httpServletRequest) {

        var fetched = _userService.findById(request.getUserId());

        if(fetched.isPresent())
        {
            var order = new Order(0, fetched.get(), request.getOrder_Date(), 10.5);

            var result = _service.save(order);
    
            return new ResponseEntity<Order>(result, HttpStatus.CREATED);    
        }
        return new ResponseEntity<ErrorResponse>(
            new ErrorResponse(HttpStatus.NOT_FOUND, 
            String.format("User with Id : %s not found", request.getUserId()), 
            httpServletRequest.getRequestURI())
            ,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(DeleteEndpoint)
    public ResponseEntity<?> DeleteOrder(@RequestParam(name = IdParam) long id, HttpServletRequest httpServletRequest) {
     
        var fetched = _service.findById(id);

        if(fetched.isPresent())
        {
            var result = fetched.get();
            if (result.id > 0) {
                _service.delete(result);
                return new ResponseEntity<Order>(result, HttpStatus.OK);
            }
        }        
        return new ResponseEntity<ErrorResponse>(
            new ErrorResponse(HttpStatus.NOT_FOUND, 
            Messages.Not_Found , 
            httpServletRequest.getRequestURI())
            ,HttpStatus.NOT_FOUND);
    }

}
