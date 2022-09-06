package nl.jtim.spring.kafka.producer.generator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static nl.jtim.spring.kafka.producer.config.KafkaTopicsConfiguration.TOPIC_NAME;

@Component
@Slf4j
public class DefaultMessageProducer implements MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public DefaultMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(Message message) {
        kafkaTemplate.send(TOPIC_NAME, message.getKey(), message.getQuote());
        log.info("Produced message with key:{}, character: {}, {}", message.getKey(), message.getCharacter(), message.getQuote());
    }
}
