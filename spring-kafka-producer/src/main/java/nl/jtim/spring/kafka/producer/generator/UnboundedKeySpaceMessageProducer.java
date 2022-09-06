package nl.jtim.spring.kafka.producer.generator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static nl.jtim.spring.kafka.producer.config.KafkaTopicsConfiguration.UNBOUNDED_KEYSPACE_TOPIC_NAME;

@Component
@Slf4j
public class UnboundedKeySpaceMessageProducer implements MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public UnboundedKeySpaceMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(Message message) {
        String key = UUID.randomUUID().toString();
        kafkaTemplate.send(UNBOUNDED_KEYSPACE_TOPIC_NAME, key, message.getQuote());
        log.info("Produced message with key:{}, character: {}, {}", key, message.getCharacter(), message.getQuote());
    }
}
