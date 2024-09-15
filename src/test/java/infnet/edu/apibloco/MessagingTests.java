package infnet.edu.apibloco;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import infnet.edu.apibloco.Domain.Contracts.Email.SendEmailRequest;
import infnet.edu.apibloco.Domain.Models.User;
import infnet.edu.apibloco.Email.OperationType;
import infnet.edu.apibloco.Email.UserEmailFactory;
import infnet.edu.apibloco.Messaging.QueueSender;

@SpringBootTest
public class MessagingTests 
{

    private final String userName = "teste";
    private final String userEmail= "marcoantoniodoom@gmail.com";
    private final String userPassword = "12345";

    @Autowired
    private QueueSender _sender;

    @Test
    void Should_Send_EmailRequest_As_Message_To_Queue() throws JsonProcessingException
    {
        var user = new User("", 
        userName, 
        userEmail, 
        userPassword);

        SendEmailRequest emailRequest = UserEmailFactory.CreateUserEmailRequest(user, OperationType.Create);
        
       _sender.send(emailRequest);

        
    }


}
