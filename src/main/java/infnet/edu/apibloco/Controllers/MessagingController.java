package infnet.edu.apibloco.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import infnet.edu.apibloco.Messaging.QueueSender;

@RestController
public class MessagingController {
    
    private final String GetEndpoint = "api/Messaging";

    @Autowired
    private QueueSender queueSender;

    @GetMapping(GetEndpoint)
    public String send(){
        queueSender.send("test message");
        return "ok. done";
    }

}   
