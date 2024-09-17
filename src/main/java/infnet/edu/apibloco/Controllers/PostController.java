package infnet.edu.apibloco.Controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import infnet.edu.apibloco.Commands.Post.Services.IPostCommandService;
import infnet.edu.apibloco.Constants.Messages;
import infnet.edu.apibloco.Domain.Aggreagates.PostAggregate;
import infnet.edu.apibloco.Domain.Contracts.Requests.Products.CreatePostRequest;
import infnet.edu.apibloco.Domain.Contracts.Requests.Products.UpdatePostRequest;
import infnet.edu.apibloco.Domain.Contracts.Responses.ErrorResponse;
import infnet.edu.apibloco.Domain.Models.Post;
import infnet.edu.apibloco.Domain.Models.User;
import infnet.edu.apibloco.Infrastructure.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
public class PostController {

    private static final String Base = "api/post";
    private static final String GetEndpoint = Base;
    private static final String GetAllEndpoint = Base + "/all";
    private static final String CreateEndpoint = Base;
    private static final String UpdateEndpoint = Base + "/update";
    private static final String DeleteEndpoint = Base;
    private static final String IdParam = "id";

    @Autowired
    private PostRepository _service;

    @Autowired
    private IPostCommandService _CommandService;

    @GetMapping(GetEndpoint)
    public ResponseEntity<?> GetPost(@RequestParam(name = IdParam) String id,
            HttpServletRequest httpServletRequest) {

        var fetched = _service.findById(id);
        if (fetched.isPresent()) {
            var result = fetched.get();
            if (!result.id.isEmpty())
                return new ResponseEntity<Post>(result, HttpStatus.OK);

        }

        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(HttpStatus.NOT_FOUND,
                        Messages.Not_Found,
                        httpServletRequest.getRequestURI()),
                HttpStatus.NOT_FOUND);

    }

    @GetMapping(GetAllEndpoint)
    public ResponseEntity<String> GetPosts() throws JsonProcessingException {

        List<Post> result = StreamSupport.stream(_service.findAll().spliterator(), false)
                .collect(Collectors.toList());

        ObjectMapper mapper = new ObjectMapper();

        return new ResponseEntity<String>(mapper.writeValueAsString(result), HttpStatus.OK);
    }

    @PostMapping(CreateEndpoint)
    public ResponseEntity<?> CreatePost(@Valid @RequestBody CreatePostRequest request, 
    BindingResult validation, HttpServletRequest httpServletRequest) {

        if(validation.hasErrors())
        {
            return ResponseEntity.badRequest().body(validation.getAllErrors().toString());
        }

        var product = new PostAggregate("", new User(
                request.getUserId(),
                "",
                "",
                ""),
                request.getDescription());

        var result = _CommandService.CreatePost(product);

        return new ResponseEntity<CompletableFuture<String>>(result, HttpStatus.OK);
    }

    @PutMapping(UpdateEndpoint)
    public ResponseEntity<?> Updateproduct(@Valid @RequestBody UpdatePostRequest request,
            HttpServletRequest httpServletRequest, BindingResult validation) {

         if(validation.hasErrors())
        {
            return ResponseEntity.badRequest().body(validation.getAllErrors().toString());
        }
        var product = new Post(
                request.getId(),
                new User(request.getUserId(),
                        "",
                        "",
                        ""),
                request.getDescription());

        var fetched = _service.findById(request.getId());
        if (fetched.isPresent()) {
            var result = fetched.get();

            if (result.Equals(product)) {
                var item = _service.save(product);
                return new ResponseEntity<Post>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(HttpStatus.NOT_FOUND,
                        Messages.Not_Found,
                        httpServletRequest.getRequestURI()),
                HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(DeleteEndpoint)
    public ResponseEntity<?> Deleteproduct(@RequestParam(name = IdParam) String id,
            HttpServletRequest httpServletRequest) {

        var fetched = _service.findById(id);
        if (fetched.isPresent()) {
            var result = fetched.get();
            if (result.description != "") {
                _service.delete(result);
                return new ResponseEntity<Post>(result, HttpStatus.OK);
            }
        }
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(HttpStatus.NOT_FOUND,
                        Messages.Not_Found,
                        httpServletRequest.getRequestURI()),
                HttpStatus.NOT_FOUND);
    }

}
