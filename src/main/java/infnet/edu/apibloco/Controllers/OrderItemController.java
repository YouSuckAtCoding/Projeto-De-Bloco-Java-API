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
import infnet.edu.apibloco.Domain.Contracts.Requests.OrderItems.CreateOrderItemRequest;
import infnet.edu.apibloco.Domain.Contracts.Responses.ErrorResponse;
import infnet.edu.apibloco.Domain.Models.OrderItem;
import infnet.edu.apibloco.Infrastructure.OrderItemRepository;
import infnet.edu.apibloco.Infrastructure.OrderRepository;
import infnet.edu.apibloco.Infrastructure.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class OrderItemController {

    
    private static final String Base = "api/orderitem";
    private static final String GetEndpoint = Base;
    private static final String GetAllEndpoint = Base + "/all";
    private static final String CreateEndpoint = Base;
    private static final String UpdateEndpoint = Base + "/update";
    private static final String DeleteEndpoint = Base;
    private static final String IdParam = "id";

    @Autowired
    private OrderItemRepository _service;
    @Autowired
    private OrderRepository _orderService;
    @Autowired
    private ProductRepository _productService;

    @GetMapping(GetEndpoint)
    public ResponseEntity<?> GetOrderItem(@RequestParam(name = IdParam) long id, 
    HttpServletRequest httpServletRequest) {

        var fetched = _service.findById(id);
        
        if(fetched.isPresent())
        {
            OrderItem result= fetched.get();
            if (result.id > 0)
                return new ResponseEntity<OrderItem>(result, HttpStatus.OK);

        }
        
        return new ResponseEntity<ErrorResponse>(
            new ErrorResponse(HttpStatus.NOT_FOUND, 
            Messages.Not_Found , 
            httpServletRequest.getRequestURI())
            ,HttpStatus.NOT_FOUND);

    }

    @GetMapping(GetAllEndpoint)
    public ResponseEntity<?> GetOrderItems(@RequestParam(name = IdParam) long id
    ,HttpServletRequest httpServletRequest) {

        var fetched = _orderService.findById(id);

        if(fetched.isPresent())
        {
            var result = fetched.get();
            if (result.id > 0) {
                List<OrderItem> items = StreamSupport.stream(_service.findOrderItemsByOrder(id).spliterator(), false)
                        .collect(Collectors.toList());
    
                return new ResponseEntity<List<OrderItem>>(items, HttpStatus.OK);
        }        
        }
     
        return new ResponseEntity<ErrorResponse>(
            new ErrorResponse(HttpStatus.NOT_FOUND, 
            Messages.Not_Found , 
            httpServletRequest.getRequestURI())
            ,HttpStatus.NOT_FOUND);
    }

    @PostMapping(CreateEndpoint)
    public ResponseEntity<?> CreateOrderItem(@RequestBody CreateOrderItemRequest request,
    HttpServletRequest httpServletRequest) {

        var fetched = _orderService.findById(request.getOrderId());

        if(fetched.isPresent())
        {
            var result = fetched.get();
            if(result.id > 0)
            {
                var product = _productService.findById(request.getProductId()).get();
    
                if(product.id > 0)
                {
                    var OrderItem = new OrderItem(0, result, product, product.price, request.getQuantity());
        
                    var item = _service.save(OrderItem);

                    _orderService.updateOrderTotal(result.id);
            
                    return new ResponseEntity<OrderItem>(item, HttpStatus.CREATED);
                }
            }
        }
       
        return new ResponseEntity<ErrorResponse>(
            new ErrorResponse(HttpStatus.BAD_REQUEST, 
            String.format("Order with Id : %s not found", request.getOrderId()), 
            httpServletRequest.getRequestURI())
            ,HttpStatus.BAD_REQUEST);   

    }

    @DeleteMapping(DeleteEndpoint)
    public ResponseEntity<?> DeleteOrderItem(@RequestParam(name = IdParam) long id,
    HttpServletRequest httpServletRequest) {
     
        var fetched = _service.findById(id);


        if(fetched.isPresent())
        {
            var result = fetched.get();
            if(result.id > 0)
            {
                _service.delete(result);
                return new ResponseEntity<OrderItem>(result, HttpStatus.OK);
            }
        }
        return new ResponseEntity<ErrorResponse>(
            new ErrorResponse(HttpStatus.NOT_FOUND, 
            Messages.Not_Found , 
            httpServletRequest.getRequestURI())
            ,HttpStatus.NOT_FOUND);
    }

}
