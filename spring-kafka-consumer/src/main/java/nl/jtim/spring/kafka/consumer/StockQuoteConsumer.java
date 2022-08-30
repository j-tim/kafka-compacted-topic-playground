package nl.jtim.spring.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StockQuoteConsumer {

    public final static String STOCK_QUOTES_TOPIC_NAME = "stock-quotes";

    @KafkaListener(topics = STOCK_QUOTES_TOPIC_NAME)
    public void on(ConsumerRecord<String, String> consumerRecord) {
        log.info("Consumed from partition: {} offset: {} key: {}, value: {}", consumerRecord.partition(), consumerRecord.offset(), consumerRecord.key(), consumerRecord.value());
    }
}
