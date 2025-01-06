package xmpl.eyaz.integration.message.restaurant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xmpl.eyaz.integration.message.command.CreateOrderCommand;
import xmpl.eyaz.integration.message.kafka.model.OrderCreatedEvent;
import xmpl.eyaz.integration.message.kafka.producer.KafkaProducer;

import java.util.UUID;

@Component
public class RestaurantEventPublisherHelper implements RestaurantEventPublisher{

    private final KafkaProducer<String, String> kafkaProducer;

    @Value("${service-order-publisher.topic.service-order-restaurant-topic-name}")
    private String TOPIC;

    public RestaurantEventPublisherHelper(KafkaProducer<String, String> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public CreateOrderCommand publish(CreateOrderCommand command) {
        String correlationId = UUID.randomUUID().toString();

        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, correlationId, getEventBodyPayload(command));

        kafkaProducer.sendRecord(record);

        /**
         * Do sth for failed publishing scenario. Like retry again or rollback payment.
         * In this example I am focusing for successful behaviour.
         */

        command.setOrderStatus("PENDING");

        return command;
    }

    private OrderCreatedEvent getOrderCreatedEvent(CreateOrderCommand command) {
        return OrderCreatedEvent.builder()
                .username(command.getUsername())
                .orderItemId(command.getOrderItemId())
                .trackingId(command.getTrackingId())
                .price(command.getPrice())
                .address(command.getAddress())
                .orderCreationTime(command.getOrderCreationTime().toString())
                .orderStatus(command.getOrderStatus())
                .restaurantId(command.getRestaurantId())
                .build();
    }

    public String getEventBodyPayload(CreateOrderCommand command) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(getOrderCreatedEvent(command));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
