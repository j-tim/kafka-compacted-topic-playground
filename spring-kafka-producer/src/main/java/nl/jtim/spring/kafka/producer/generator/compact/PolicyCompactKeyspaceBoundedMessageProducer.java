package nl.jtim.spring.kafka.producer.generator.compact;

import lombok.extern.slf4j.Slf4j;
import nl.jtim.spring.kafka.producer.generator.Message;
import nl.jtim.spring.kafka.producer.generator.MessageProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static nl.jtim.spring.kafka.producer.config.KafkaTopicsConfiguration.TOPIC_WITH_CLEANUP_POLICY_COMPACT_KEYSPACE_BOUNDED;

@Component
@Slf4j
public class PolicyCompactKeyspaceBoundedMessageProducer implements MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PolicyCompactKeyspaceBoundedMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(Message message) {
        kafkaTemplate.send(TOPIC_WITH_CLEANUP_POLICY_COMPACT_KEYSPACE_BOUNDED, message.getKey(), message.getQuote());
        log.info("Produced message with key: {}, character: {}, {}", message.getKey(), message.getCharacter(), message.getQuote());
    }
}
