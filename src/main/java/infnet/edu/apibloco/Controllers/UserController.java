package infnet.edu.apibloco.Controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import infnet.edu.apibloco.Domain.Models.User;
import infnet.edu.apibloco.Infrastructure.UserRepository;

@RestController
public class UserController {
 
    @Autowired
    private UserRepository _service;

    private static final String Base = "api/user";
    private static final String GetAllEndpoint = Base;
    private static final String CreateEndpoint = Base;

     @GetMapping(GetAllEndpoint)
    public List<User> GetUsers() {

        List<User> result = 
          StreamSupport.stream(_service.findAll().spliterator(), false)
         .collect(Collectors.toList());
        
        return result;
    }

}
