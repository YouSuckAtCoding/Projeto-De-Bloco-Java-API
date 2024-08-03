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

import infnet.edu.apibloco.Domain.Contracts.Requests.User.CreateUserRequest;
import infnet.edu.apibloco.Domain.Contracts.Requests.User.UpdateUserRequest;
import infnet.edu.apibloco.Domain.Models.User;
import infnet.edu.apibloco.Infrastructure.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository _service;

    private static final String Base = "api/user";
    private static final String GetEndpoint = Base;
    private static final String GetAllEndpoint = Base + "/all";
    private static final String CreateEndpoint = Base;
    private static final String UpdateEndpoint = Base + "/update";
    private static final String DeleteEndpoint = Base;
    private static final String IdParam = "id";

    @GetMapping(GetEndpoint)
    public ResponseEntity<?> GetUsers(@RequestParam(name = IdParam) long id) {

        User fetched = _service.findById(id).get();

        if (fetched.id > 0)
            return new ResponseEntity<User>(fetched, HttpStatusCode.valueOf((200)));

        return new ResponseEntity<>(HttpStatusCode.valueOf((404)));

    }

    @GetMapping(GetAllEndpoint)
    public ResponseEntity<List<User>> GetUsers() {

        List<User> result = StreamSupport.stream(_service.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return new ResponseEntity<List<User>>(result, HttpStatusCode.valueOf((200)));
    }

    @PostMapping(CreateEndpoint)
    public ResponseEntity<?> CreateUser(@RequestBody CreateUserRequest request) {
        var user = new User(0, request.getName(), request.getEmail(), request.getPassword());

        var result = _service.save(user);

        return new ResponseEntity<User>(result, HttpStatusCode.valueOf((201)));

    }

    @PutMapping(UpdateEndpoint)
    public ResponseEntity<?> UpdateUser(@RequestBody UpdateUserRequest request) {
        var user = new User(request.getId(), request.getName(), request.getEmail(), request.getPassword());

        var fetched = _service.findById(request.getId()).get();

        if (fetched.Equals(user)) {
            var result = _service.save(user);

            return new ResponseEntity<User>(result, HttpStatusCode.valueOf((200)));
        }

        return new ResponseEntity<>(HttpStatusCode.valueOf((404)));
    }

    @DeleteMapping(DeleteEndpoint)
    public ResponseEntity<?> DeleteUser(@RequestParam(name = IdParam) long id) {
        User fetched = _service.findById(id).get();
        if (fetched.Name != "") {
            _service.delete(fetched);
            return new ResponseEntity<User>(fetched, HttpStatusCode.valueOf((200)));
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf((404)));
    }
}
