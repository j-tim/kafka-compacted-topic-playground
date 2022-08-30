package nl.jtim.spring.kafka.producer.config;

import nl.jtim.spring.kafka.producer.generator.RandomStockQuoteGenerator;
import nl.jtim.spring.kafka.producer.generator.ScheduledStockQuoteProducer;
import nl.jtim.spring.kafka.producer.generator.StockQuoteProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfiguration {

    @Bean
    @ConditionalOnProperty(name = "kafka.producer.enabled", havingValue = "true")
    public ScheduledStockQuoteProducer kafkaProducer(StockQuoteProducer producer, RandomStockQuoteGenerator generator) {
        return new ScheduledStockQuoteProducer(producer, generator);
    }

}
