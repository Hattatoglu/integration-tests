package xmpl.eyaz.integration.message.kafka.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.stereotype.Component;
import xmpl.eyaz.integration.message.kafka.model.OrderCreatedEvent;
import xmpl.eyaz.integration.message.restaurant.config.IT;

import java.time.Duration;
import java.util.*;

public class KafkaTestConsumer {

    @Autowired
    private KafkaConsumerConfigData kafkaConsumerConfigData;

    @Value("${service-order-consumer.topic.service-order-restaurant-topic-name}")
    private String TOPIC;

    public Consumer<String, String> consumer ;

    public void initiateTestConsumer() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerConfigData.getBootstrapServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaConsumerConfigData.getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaConsumerConfigData.getValueDeserializer());
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, kafkaConsumerConfigData.getValueDeserializer());
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, kafkaConsumerConfigData.getKeyDeserializer());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConsumerConfigData.getAutoOffsetReset());
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, kafkaConsumerConfigData.getSessionTimeoutMs());
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, kafkaConsumerConfigData.getHeartbeatIntervalMs());
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, kafkaConsumerConfigData.getMaxPollIntervalMs());
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG,
                kafkaConsumerConfigData.getMaxPartitionFetchBytesDefault() *
                        kafkaConsumerConfigData.getMaxPartitionFetchBytesBoostFactor());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaConsumerConfigData.getMaxPollRecords());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");

        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(TOPIC));
    }

    public OrderCreatedEvent getMessageFromQueue() {
        List<OrderCreatedEvent> events = new ArrayList<>();
        consumer.poll(Duration.ofSeconds(5)).forEach(record -> {
            events.add(getContentCreatedEventBody(record.value()));
        });
        return events.getFirst();
    }

    public void closeConsumer() {
        if (consumer != null) {
            consumer.close();
        }
    }

    private OrderCreatedEvent getContentCreatedEventBody(String eventBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(eventBody, OrderCreatedEvent.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
