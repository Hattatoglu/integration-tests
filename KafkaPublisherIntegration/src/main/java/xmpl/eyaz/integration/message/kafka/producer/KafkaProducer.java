package xmpl.eyaz.integration.message.kafka.producer;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.Serializable;


public interface KafkaProducer<K extends Serializable, V extends Serializable> {
    void sendRecord(ProducerRecord<K, V> record);
}
