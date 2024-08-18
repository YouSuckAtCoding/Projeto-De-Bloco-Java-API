package infnet.edu.apibloco.Domain.Contracts.Responses;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    public HttpStatus statusCode;
    public String Message;
    public String url;
    
    public ErrorResponse(HttpStatus statusCode, String message, String url) {
        this.statusCode = statusCode;
        Message = message;
        this.url = url;
    }

}
