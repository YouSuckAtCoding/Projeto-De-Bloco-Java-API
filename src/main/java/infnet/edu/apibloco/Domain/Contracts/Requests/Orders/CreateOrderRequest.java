package infnet.edu.apibloco.Domain.Contracts.Requests.Orders;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


public class CreateOrderRequest {

    public String userId;
    
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    public LocalDateTime order_Date;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public LocalDateTime getOrder_Date() {
        return order_Date;
    }
    public void setOrder_Date(LocalDateTime order_Date) {
        this.order_Date = order_Date;
    }

}
