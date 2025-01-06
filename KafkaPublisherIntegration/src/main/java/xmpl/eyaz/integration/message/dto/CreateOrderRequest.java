package xmpl.eyaz.integration.message.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateOrderRequest {

    @NotNull private final String username;
    @NotNull private final String orderItemId;
    @NotNull private final BigDecimal price;
    @NotNull private final String restaurantId;
    @NotNull private final String address;

    public CreateOrderRequest(String username, String orderItemId, BigDecimal price, String restaurantId, String address) {
        this.username = username;
        this.orderItemId = orderItemId;
        this.price = price;
        this.restaurantId = restaurantId;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getAddress() {
        return address;
    }
}
