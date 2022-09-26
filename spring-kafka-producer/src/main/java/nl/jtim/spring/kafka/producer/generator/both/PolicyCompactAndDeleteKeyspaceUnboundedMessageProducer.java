package nl.jtim.spring.kafka.producer.generator.both;

import lombok.extern.slf4j.Slf4j;
import nl.jtim.spring.kafka.producer.generator.Message;
import nl.jtim.spring.kafka.producer.generator.MessageProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.lang.String.valueOf;
import static nl.jtim.spring.kafka.producer.config.KafkaTopicsConfiguration.TOPIC_WITH_DELETE_AND_CLEANUP_KEYSPACE_UNBOUNDED;

@Component
@Slf4j
public class PolicyCompactAndDeleteKeyspaceUnboundedMessageProducer implements MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PolicyCompactAndDeleteKeyspaceUnboundedMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(Message message) {
        String key = valueOf(UUID.randomUUID());
        kafkaTemplate.send(TOPIC_WITH_DELETE_AND_CLEANUP_KEYSPACE_UNBOUNDED, key, message.getQuote());

        log.info("Produced message with key: {}, character: {}, {}", key, message.getCharacter(), message.getQuote());
    }
}
