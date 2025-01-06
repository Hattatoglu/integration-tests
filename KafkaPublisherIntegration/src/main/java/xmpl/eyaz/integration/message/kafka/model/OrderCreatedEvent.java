package xmpl.eyaz.integration.message.kafka.model;

import java.math.BigDecimal;

public class OrderCreatedEvent {

    private String username;
    private String orderItemId;
    private String trackingId;
    private BigDecimal price;
    private String address;
    private String orderCreationTime;
    private String orderStatus;
    private String restaurantId;

    public OrderCreatedEvent() {
    }

    private OrderCreatedEvent(Builder builder) {
        this.username = builder.username;
        this.orderItemId = builder.orderItemId;
        this.trackingId = builder.trackingId;
        this.price = builder.price;
        this.address = builder.address;
        this.orderCreationTime = builder.orderCreationTime;
        this.orderStatus = builder.orderStatus;
        this.restaurantId = builder.restaurantId;
    }

    public String getUsername() {
        return username;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public String getOrderCreationTime() {
        return orderCreationTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String username;
        private String orderItemId;
        private String trackingId;
        private BigDecimal price;
        private String address;
        private String orderCreationTime;
        private String orderStatus;
        private String restaurantId;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder orderItemId(String orderItemId) {
            this.orderItemId = orderItemId;
            return this;
        }

        public Builder trackingId(String trackingId) {
            this.trackingId = trackingId;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder orderCreationTime(String orderCreationTime) {
            this.orderCreationTime = orderCreationTime;
            return this;
        }

        public Builder orderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder restaurantId(String restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public OrderCreatedEvent build() {
            return new OrderCreatedEvent(this);
        }

    }
}
