package nl.jtim.spring.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    public final static String MESSAGES_TOPIC_NAME = "messages-topic";

    @KafkaListener(topics = MESSAGES_TOPIC_NAME)
    public void on(ConsumerRecord<String, String> consumerRecord) {
        log.info("Consumed from partition: {} offset: {} key: {}, value: {}", consumerRecord.partition(), consumerRecord.offset(), consumerRecord.key(), consumerRecord.value());
    }
}
