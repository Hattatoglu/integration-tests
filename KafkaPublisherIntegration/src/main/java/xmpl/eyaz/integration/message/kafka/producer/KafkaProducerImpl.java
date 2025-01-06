package xmpl.eyaz.integration.message.kafka.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@Component
public class KafkaProducerImpl<K extends Serializable, V extends Serializable> implements KafkaProducer<K, V>{

    private final KafkaTemplate<K, V> kafkaTemplate;

    public KafkaProducerImpl(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendRecord(ProducerRecord<K, V> record) {
        kafkaTemplate.send(record);
    }
}
