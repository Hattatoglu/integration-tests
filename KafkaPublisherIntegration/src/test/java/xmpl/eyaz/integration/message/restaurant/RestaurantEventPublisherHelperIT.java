package xmpl.eyaz.integration.message.restaurant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xmpl.eyaz.integration.message.command.CreateOrderCommand;
import xmpl.eyaz.integration.message.kafka.KafkaTestConsumer;
import xmpl.eyaz.integration.message.kafka.model.OrderCreatedEvent;
import xmpl.eyaz.integration.message.restaurant.support.RestaurantEventPublisherHelperTestSupport;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class RestaurantEventPublisherHelperIT extends RestaurantEventPublisherHelperTestSupport {

    @Autowired
    private RestaurantEventPublisherHelper restaurantEventPublisherHelper;

    @Autowired
    private KafkaTestConsumer kafkaTestConsumer;

    @BeforeEach
    void setUp() {
        kafkaTestConsumer.initiateTestConsumer();
    }

    @Test
    void should_sendOrderCreatedEvent() {

        //given
        CreateOrderCommand command = getCreateOrderCommand();

        //when
        CreateOrderCommand answer = restaurantEventPublisherHelper.publish(command);

        //then
        OrderCreatedEvent event = kafkaTestConsumer.getMessageFromQueue();


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

    @Test
    void should_throwException_whenCommandNotProper() {
        // given
        CreateOrderCommand command = getCreateOrderCommandWithNullFields();

        // when
        Throwable answer = assertThrows(Throwable.class, () -> restaurantEventPublisherHelper.publish(command));

        //then
        Assertions.assertThat(answer).isNotNull();
        //Assertions.assertThat(answer.getMessage()).isEqualTo("account.not.found");
    }

    @AfterEach
    void tearDown() {
        kafkaTestConsumer.closeConsumer();
    }
}