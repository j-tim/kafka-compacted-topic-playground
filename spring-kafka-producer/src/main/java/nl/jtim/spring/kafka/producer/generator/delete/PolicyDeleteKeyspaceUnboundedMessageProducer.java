package nl.jtim.spring.kafka.producer.generator.delete;

import lombok.extern.slf4j.Slf4j;
import nl.jtim.spring.kafka.producer.generator.Message;
import nl.jtim.spring.kafka.producer.generator.MessageProducer;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.lang.String.valueOf;
import static nl.jtim.spring.kafka.producer.config.KafkaTopicsConfiguration.TOPIC_WITH_CLEANUP_POLICY_DELETE_KEYSPACE_UNBOUNDED;

@Component
@Slf4j
public class PolicyDeleteKeyspaceUnboundedMessageProducer implements MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PolicyDeleteKeyspaceUnboundedMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(Message message) {
        String key = valueOf(UUID.randomUUID());
        kafkaTemplate.send(TOPIC_WITH_CLEANUP_POLICY_DELETE_KEYSPACE_UNBOUNDED, key, message.getQuote());

        log.info("Produced message with key: {}, character: {}, {}", key, message.getCharacter(), message.getQuote());
    }
}
