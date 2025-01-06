package xmpl.eyaz.integration.message.command;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class CreateOrderCommand {

    private final String username;
    private final String orderItemId;
    private final BigDecimal price;
    private final String restaurantId;
    private final String address;

    private ZonedDateTime orderCreationTime;
    private String trackingId;
    private String orderStatus;

    private CreateOrderCommand(Builder builder) {
        this.username = builder.username;
        this.orderItemId = builder.orderItemId;
        this.price = builder.price;
        this.restaurantId = builder.restaurantId;
        this.address = builder.address;
        this.orderStatus = builder.orderStatus;
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

    public ZonedDateTime getOrderCreationTime() {
        return orderCreationTime;
    }

    public void setOrderCreationTime(ZonedDateTime orderCreationTime) {
        this.orderCreationTime = orderCreationTime;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String username;
        private String orderItemId;
        private BigDecimal price;
        private String restaurantId;
        private String address;
        private String orderStatus;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder orderItemId(String orderItemId) {
            this.orderItemId = orderItemId;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder restaurantId(String restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder orderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public CreateOrderCommand build() {
            return new CreateOrderCommand(this);
        }
    }
}
