package infnet.edu.apibloco;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import infnet.edu.apibloco.Domain.Models.Post;
import infnet.edu.apibloco.Domain.Models.User;
import infnet.edu.apibloco.Infrastructure.PostRepository;

@SpringBootTest
public class PostTests {

    private static final String PostDescription = "Produto teste";
    @Autowired
    private PostRepository _postRepository;

    @Test
	void Should_Return_Posts_From_PostRepository()
	{
		var result = _postRepository.findAll();
		Assert.notEmpty(result, "Posts retrieved");
	}

    @Test
    void Should_Create_Post_In_PostRepository()
    {
        var post = new Post(UUID.randomUUID().toString(), new User(), PostDescription);

        var created = _postRepository.save(post);
        var result = _postRepository.findById(created.id).get();

        Assert.notNull(result, "User Created"); 
    }

    @Test
    void Should_Delete_Post_From_PostRepository()
    {
        var post = new Post(UUID.randomUUID().toString(), new User(), PostDescription);

        var created = _postRepository.save(post);
        _postRepository.delete(created);
        var result = _postRepository.findById(created.id);

        Assert.isTrue(result.isPresent() == false, "User Deleted");

    }

    @Test
    void Should_Update_Post_In_PostRepository()
    {
        String newDesc = "Post123";
        var post = new Post(UUID.randomUUID().toString(), new User(), PostDescription);
        
        var created = _postRepository.save(post);
        created.description = newDesc;
        var result = _postRepository.save(created);

        Assert.isTrue(!(result.description != newDesc), "Post Updated");
    }
}
