package infnet.edu.apibloco;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import infnet.edu.apibloco.Domain.Models.User;
import infnet.edu.apibloco.Infrastructure.UserRepository;

@SpringBootTest
public class UserTests {
    
    @Autowired
	private UserRepository _userRepository;
	    
    private final String userName = "teste";
    private final String userEmail= "olokobicho@email.com";
    private final String userPassword = "12345";

    @Test
	void Should_Return_Users_From_UserRepository()
	{
		var result = _userRepository.findAll();
		Assert.notNull(result, "Users retrieved");
	}
    @Test
	void Should_Insert_User_In_UserRepository()
    {
        var user = new User(0, userName, userEmail, userPassword);

        var created = _userRepository.save(user);
        var result = _userRepository.findById(created.id);

        Assert.notNull(result.isPresent(), "User Created");        
    }
    @Test
	void Should_Delete_User_In_UserRepository()
    {
        var users = _userRepository.findAll();

        var lastUser = users.get(users.size() - 1);
        _userRepository.delete(lastUser);
        var result = _userRepository.findById(lastUser.id);

        Assert.isTrue(!result.isPresent(), "User Deleted");
    }
    @Test
    void Should_Update_User_In_UserRepository()
    {
      
        var user = new User(0,userName, userEmail, userPassword);

        var created = _userRepository.save(user);
        created.Name = "Teste666";
        var updated = _userRepository.save(created);

        var result = updated.Name != userName;

        Assert.isTrue(result, "User Created");        
    }





}
