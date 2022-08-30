package nl.jtim.spring.kafka.producer.generator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StockQuoteProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public StockQuoteProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(StockQuote stockQuote) {
        kafkaTemplate.send("stock-quotes", stockQuote.getSymbol(), stockQuote.getPrice());
        log.info("Produced stock quote: {}", stockQuote);
    }
}
