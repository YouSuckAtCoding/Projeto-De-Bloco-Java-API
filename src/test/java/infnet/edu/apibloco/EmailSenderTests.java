package infnet.edu.apibloco;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import infnet.edu.apibloco.Domain.Contracts.Email.SendEmailRequest;
import infnet.edu.apibloco.Infrastructure.Services.EmailSenderService;

@SpringBootTest
public class EmailSenderTests {


    @Autowired
    private EmailSenderService _service;

    @Test
    void Should_Send_Email_To_Email_Microservice()
    {
        SendEmailRequest request = new SendEmailRequest();
        request.setEmailAddress("marcoantoniodoom@gmail.com");
        request.setSubject("From main api to service test");
        request.setContent("Fuck this shit i'm out");

        var result = _service.SendEmail(request);

        StepVerifier.create(result)
		.consumeNextWith(x -> {
			System.out.println(x);
		})
		.verifyComplete();
    }


}
