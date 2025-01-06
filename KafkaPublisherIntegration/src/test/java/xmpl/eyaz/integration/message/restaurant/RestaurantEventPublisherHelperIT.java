package xmpl.eyaz.integration.message.restaurant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import xmpl.eyaz.integration.message.command.CreateOrderCommand;
import xmpl.eyaz.integration.message.kafka.config.KafkaTestConsumer;
import xmpl.eyaz.integration.message.kafka.model.OrderCreatedEvent;
import xmpl.eyaz.integration.message.restaurant.support.TestSupport;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class RestaurantEventPublisherHelperIT extends TestSupport {

    private RestaurantEventPublisherHelper restaurantEventPublisherHelper;

    @BeforeEach
    void setUp() {
        initiateTestConsumer();
        this.restaurantEventPublisherHelper = getRestaurantEventPublisherHelper();
    }

    @Test
    void should_sendOrderCreatedEvent() {

        CreateOrderCommand command = CreateOrderCommand.builder()
                .username("test-username")
                .orderItemId("test-orderItemId-1234")
                .price(BigDecimal.valueOf(123.45))
                .restaurantId("test-restaurantId")
                .address("test-address")
                .orderStatus("PAID")
                .build();

        command.setOrderCreationTime(ZonedDateTime.now(ZoneOffset.UTC));
        command.setTrackingId(UUID.randomUUID().toString());

        CreateOrderCommand answer = restaurantEventPublisherHelper.publish(command);

        OrderCreatedEvent event = getMessageFromQueue();

        assertThat(answer).isNotNull();
        assertThat(answer.getOrderStatus()).isNotNull().isEqualTo("PENDING");

        assertThat(event).isNotNull();
        assertThat(event.getUsername()).isNotNull().isEqualTo(command.getUsername());
        assertThat(event.getOrderItemId()).isNotNull().isEqualTo(command.getOrderItemId());
        assertThat(event.getPrice()).isNotNull().isEqualTo(command.getPrice());
        assertThat(event.getRestaurantId()).isNotNull().isEqualTo(command.getRestaurantId());
        assertThat(event.getAddress()).isNotNull().isEqualTo(command.getAddress());
        assertThat(event.getOrderStatus()).isNotNull().isEqualTo("PAID");
        assertThat(event.getOrderCreationTime()).isNotNull().isEqualTo(command.getOrderCreationTime().toString());
        assertThat(event.getTrackingId()).isNotNull().isEqualTo(command.getTrackingId());

    }

    @AfterEach
    void tearDown() {
        closeConsumer();
    }
}