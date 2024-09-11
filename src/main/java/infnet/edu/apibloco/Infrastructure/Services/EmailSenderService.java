package infnet.edu.apibloco.Infrastructure.Services;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

import infnet.edu.apibloco.Domain.Contracts.Email.SendEmailRequest;
import reactor.core.publisher.Mono;

@Service
public class EmailSenderService {

    private WebClient CreateWebClient() {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8080/api/Email/send")
                .build();

        return client;
    }
    
    public Mono<String> SendEmail(SendEmailRequest request)
    {
        WebClient client = CreateWebClient();

        return client.post()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
        .retrieve()
        .bodyToMono(String.class);

    }

}
