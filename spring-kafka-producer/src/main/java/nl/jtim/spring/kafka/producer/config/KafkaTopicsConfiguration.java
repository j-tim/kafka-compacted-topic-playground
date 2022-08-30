package nl.jtim.spring.kafka.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.concurrent.TimeUnit;

import static java.lang.String.valueOf;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * https://docs.confluent.io/platform/current/installation/configuration/topic-configs.html
 */
@Configuration
public class KafkaTopicsConfiguration {

    public final static String STOCK_QUOTES_TOPIC_NAME = "stock-quotes";

    @Bean
    @ConditionalOnProperty(name = "kafka.producer.enabled", havingValue = "true")
    public NewTopic stockQuotesTopic() {
        return TopicBuilder.name(STOCK_QUOTES_TOPIC_NAME)
            .config("retention.ms", valueOf(MINUTES.toMillis(2)))
            .config("segment.ms", valueOf(SECONDS.toMillis(30)))
            // The time to wait before deleting a file from the filesystem. Default 60000 (1 minute)
            .config("file.delete.delay.ms", valueOf(SECONDS.toMillis(30)))
            .partitions(1)
            .build();
    }
}
