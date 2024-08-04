package infnet.edu.apibloco.Domain.Contracts.Requests.OrderItems;

public class CreateOrderItemRequest {

    private long orderId;
    private long productId;
    private int quantity;
    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    
    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }
   
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
