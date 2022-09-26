package nl.jtim.spring.kafka.producer.generator.delete;

import lombok.extern.slf4j.Slf4j;
import nl.jtim.spring.kafka.producer.generator.Message;
import nl.jtim.spring.kafka.producer.generator.MessageProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static nl.jtim.spring.kafka.producer.config.KafkaTopicsConfiguration.TOPIC_WITH_CLEANUP_POLICY_DELETE_KEYSPACE_BOUNDED;

@Component
@Slf4j
public class PolicyDeleteKeyspaceBoundedMessageProducer implements MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PolicyDeleteKeyspaceBoundedMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(Message message) {
        kafkaTemplate.send(TOPIC_WITH_CLEANUP_POLICY_DELETE_KEYSPACE_BOUNDED, message.getKey(), message.getQuote());
        log.info("Produced message with key: {}, character: {}, {}", message.getKey(), message.getCharacter(), message.getQuote());
    }
}
