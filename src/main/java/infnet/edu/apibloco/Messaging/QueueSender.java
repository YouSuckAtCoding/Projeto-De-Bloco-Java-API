package infnet.edu.apibloco.Messaging;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import infnet.edu.apibloco.Domain.Contracts.Email.SendEmailRequest;

@Component
public class QueueSender {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send(SendEmailRequest message) throws JsonProcessingException, AmqpException {

        ObjectMapper mapper = new ObjectMapper();
        rabbitTemplate.convertAndSend(this.queue.getName(), mapper.writeValueAsString(message));

    }
}
