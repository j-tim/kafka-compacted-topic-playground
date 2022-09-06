package nl.jtim.spring.kafka.producer.config;

import lombok.extern.slf4j.Slf4j;
import nl.jtim.spring.kafka.producer.generator.MessageProducer;
import nl.jtim.spring.kafka.producer.generator.RandomMessageGenerator;
import nl.jtim.spring.kafka.producer.generator.ScheduledMessageProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
public class KafkaProducerConfiguration {

    public KafkaProducerConfiguration() {
        log.info("KafkaProducerConfiguration initialized");
    }

    @Bean
    @ConditionalOnProperty(name = "kafka.producer.enabled", havingValue = "true")
    public ScheduledMessageProducer kafkaProducer(List<MessageProducer> messageProducers, RandomMessageGenerator generator) {
        return new ScheduledMessageProducer(messageProducers, generator);
    }
}
