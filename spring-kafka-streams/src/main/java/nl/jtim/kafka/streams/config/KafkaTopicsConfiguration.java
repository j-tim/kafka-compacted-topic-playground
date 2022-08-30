package nl.jtim.kafka.streams.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static java.lang.String.valueOf;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * https://docs.confluent.io/platform/current/installation/configuration/topic-configs.html
 */
@Configuration
public class KafkaTopicsConfiguration {

    public final static String STOCK_QUOTES_TOPIC_NAME = "stock-quotes";
    public final static String STOCK_QUOTES_COMPACT_TOPIC_NAME = "stock-quotes-compact";
    public final static String STOCK_QUOTES_COMPACT_DELETE_TOPIC_NAME = "stock-quotes-compact-and-delete";

    @Bean
    public NewTopic stockQuotesCompactedTopic() {
        return TopicBuilder.name(STOCK_QUOTES_COMPACT_TOPIC_NAME)
            .config("segment.ms", "5000")
            .config("min.cleanable.dirty.ratio", "0.001")
            .partitions(1)
            .compact()
            .build();
    }

//    @Bean
//    public NewTopic stockQuotesCompactAndDeleteTopic() {
//        return TopicBuilder.name(STOCK_QUOTES_COMPACT_DELETE_TOPIC_NAME)
//            .config("segment.ms", "5000")
//            .config("min.cleanable.dirty.ratio", "0.001")
//            .config("cleanup.policy", "[compact,delete]")
//            .partitions(1)
//            .compact()
//            .build();
//    }
}
