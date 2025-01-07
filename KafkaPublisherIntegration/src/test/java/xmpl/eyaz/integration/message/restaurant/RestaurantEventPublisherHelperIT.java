package xmpl.eyaz.integration.message.restaurant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.context.EmbeddedKafka;
import xmpl.eyaz.integration.message.command.CreateOrderCommand;
import xmpl.eyaz.integration.message.kafka.KafkaTestConsumer;
import xmpl.eyaz.integration.message.kafka.model.OrderCreatedEvent;
import xmpl.eyaz.integration.message.restaurant.config.IT;
import xmpl.eyaz.integration.message.restaurant.support.RestaurantEventPublisherHelperTestSupport;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@IT
@EmbeddedKafka(partitions = 1, topics = {"service-order-restaurant-test-topic"})
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

    @AfterEach
    void tearDown() {
        kafkaTestConsumer.closeConsumer();
    }
}