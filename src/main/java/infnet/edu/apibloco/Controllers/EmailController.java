package infnet.edu.apibloco.Controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import infnet.edu.apibloco.Domain.Contracts.Email.SendEmailRequest;
import infnet.edu.apibloco.Domain.Models.EmailInfo;
import infnet.edu.apibloco.Infrastructure.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

@RestController
public class EmailController {

    private static final String Base = "api/email";
    private static final String GetEndpoint = Base;
    private static final String GetAllEndpoint = Base + "/all";
    private static final String CreateEndpoint = Base;
    private static final String DeleteEndpoint = Base;
    private static final String IdParam = "id";
    private static final String UserIdParam = "userId";

    @Autowired
    private EmailRepository _EmailRepository;

    @PostMapping(CreateEndpoint)
    public ResponseEntity<?> CreateEmail(@RequestBody SendEmailRequest request) 
    throws AddressException, MessagingException
    {
        var EmailInfo = new EmailInfo(0, 
                                        request.getEmail(), 
                                        request.getContent(), 
                                        request.getAttatchement(), 
                                        LocalDateTime.now(),
                                        request.getUserId());
        
        EmailInfo.SendEmailRequest(EmailInfo);
        var result = _EmailRepository.save(EmailInfo);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
