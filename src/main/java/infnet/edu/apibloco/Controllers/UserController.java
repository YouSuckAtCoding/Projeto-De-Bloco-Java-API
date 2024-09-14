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

import com.fasterxml.jackson.core.JsonProcessingException;
import infnet.edu.apibloco.Constants.Messages;
import infnet.edu.apibloco.Domain.Contracts.Email.SendEmailRequest;
import infnet.edu.apibloco.Domain.Contracts.Requests.User.CreateUserRequest;
import infnet.edu.apibloco.Domain.Contracts.Requests.User.UpdateUserRequest;
import infnet.edu.apibloco.Domain.Contracts.Responses.ErrorResponse;
import infnet.edu.apibloco.Domain.Models.User;
import infnet.edu.apibloco.Email.OperationType;
import infnet.edu.apibloco.Email.UserEmailFactory;
import infnet.edu.apibloco.Infrastructure.UserRepository;
import infnet.edu.apibloco.Infrastructure.Services.EmailSenderService;
import infnet.edu.apibloco.Messaging.QueueSender;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserRepository _service;
    @Autowired 
    private QueueSender _queueSender;

    @Autowired  
    private EmailSenderService _emailService;

    private static final String Base = "api/user";
    private static final String GetEndpoint = Base;
    private static final String GetAllEndpoint = Base + "/all";
    private static final String CreateEndpoint = Base;
    private static final String UpdateEndpoint = Base + "/update";
    private static final String DeleteEndpoint = Base;
    private static final String IdParam = "id";

    @GetMapping(GetEndpoint)
    public ResponseEntity<?> GetUsers(@RequestParam(name = IdParam) long id,
    HttpServletRequest httpServletRequest) {

        var fetched = _service.findById(id);

        if(fetched.isPresent()){
        
            var result = fetched.get();

            if (result.id > 0)
               return new ResponseEntity<User>(result, HttpStatus.OK);
        }     
        return new ResponseEntity<ErrorResponse>(
            new ErrorResponse(HttpStatus.NOT_FOUND, 
            Messages.Not_Found , 
            httpServletRequest.getRequestURI())
            ,HttpStatus.NOT_FOUND);
    }

    @GetMapping(GetAllEndpoint)
    public ResponseEntity<List<User>> GetUsers() {

        List<User> result = StreamSupport.stream(_service.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return new ResponseEntity<List<User>>(result, HttpStatus.OK);
    }

    @PostMapping(CreateEndpoint)
    public ResponseEntity<?> CreateUser(@RequestBody CreateUserRequest request) throws JsonProcessingException {

        var user = new User(0, 
        request.getName(), 
        request.getEmail(), 
        request.getPassword());

        var result = _service.save(user);

        SendEmailRequest emailRequest = UserEmailFactory.CreateUserEmailRequest(result, OperationType.Create);
        _queueSender.send(emailRequest);
        
        return new ResponseEntity<User>(result, HttpStatus.CREATED);
    }


    @PutMapping(UpdateEndpoint)
    public ResponseEntity<?> UpdateUser(@RequestBody UpdateUserRequest request,
     HttpServletRequest httpServletRequest) {

        var fetched = _service.findById(request.getId());

        if(fetched.isPresent())
        {
            var result = fetched.get();
            var user = new User(request.getId(), request.getName(), request.getEmail(), request.getPassword());
            if (result.Equals(user))
            {
                var item = _service.save(user);
                
                SendEmailRequest emailRequest = UserEmailFactory.CreateUserEmailRequest(result, OperationType.Update);

                _emailService.SendEmail(emailRequest);
               
                return new ResponseEntity<User>(item, HttpStatus.OK);
            }
        }

        return new ResponseEntity<ErrorResponse>(
            new ErrorResponse(HttpStatus.NOT_FOUND, 
            Messages.Not_Found , 
            httpServletRequest.getRequestURI())
            ,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(DeleteEndpoint)
    public ResponseEntity<?> DeleteUser(@RequestParam(name = IdParam) long id,
     HttpServletRequest httpServletRequest) {
        
        var fetched = _service.findById(id);

        if(fetched.isPresent())
        {
            var result = fetched.get();
            if (!result.Name.isEmpty()) {

                _service.delete(result);

                SendEmailRequest emailRequest = UserEmailFactory.CreateUserEmailRequest(result, OperationType.Delete);

                _emailService.SendEmail(emailRequest);
        
                return new ResponseEntity<User>(result, HttpStatus.OK);
            }
        }
         return new ResponseEntity<ErrorResponse>(
            new ErrorResponse(HttpStatus.NOT_FOUND, 
            Messages.Not_Found , 
            httpServletRequest.getRequestURI())
            ,HttpStatus.NOT_FOUND);
    }
}
