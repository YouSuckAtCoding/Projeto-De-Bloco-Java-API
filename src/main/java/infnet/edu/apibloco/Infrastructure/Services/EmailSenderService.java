package infnet.edu.apibloco.Infrastructure.Services;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

import infnet.edu.apibloco.Domain.Contracts.Email.SendEmailRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmailSenderService {

    private final String Base = "http://localhost:8080/api/Email/";
    private final String PostEndpoint = Base + "send";
    private final String GetByEmailEndpoint = Base + "getEmails?emailAddress=";
    private final String DeleteEmails = Base + "delete?emailAddress=";

    private WebClient CreateWebClient(String endpoint) {

        WebClient client = WebClient.builder()
                .baseUrl(endpoint)
                .build();

        return client;
    }
    
    public Mono<String> DeleteEmails(String emailAddress)
    {
        WebClient client = CreateWebClient(DeleteEmails);

        return client.delete().retrieve().bodyToMono(String.class);
    }

    public Flux<String>GetEmailsByUser(String emailAddress)
    {
        WebClient client = CreateWebClient(GetByEmailEndpoint +  emailAddress);

        return client.get().retrieve().bodyToFlux(String.class);
    }

    public Mono<String> SendEmail(SendEmailRequest request)
    {
        WebClient client = CreateWebClient(PostEndpoint);

        return client.post()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
        .retrieve()
        .bodyToMono(String.class);

    }

}
