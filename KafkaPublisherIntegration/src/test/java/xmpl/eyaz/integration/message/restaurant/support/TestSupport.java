package xmpl.eyaz.integration.message.restaurant.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.context.EmbeddedKafka;
import xmpl.eyaz.integration.message.kafka.config.KafkaTestConsumer;
import xmpl.eyaz.integration.message.restaurant.RestaurantEventPublisherHelper;
import xmpl.eyaz.integration.message.restaurant.config.IT;


@IT
@EmbeddedKafka(partitions = 1, topics = {"service-order-restaurant-test-topic"})
public class TestSupport extends KafkaTestConsumer{

    @Autowired
    private RestaurantEventPublisherHelper restaurantEventPublisherHelper;

    protected RestaurantEventPublisherHelper getRestaurantEventPublisherHelper() {
        return this.restaurantEventPublisherHelper;
    }
}
