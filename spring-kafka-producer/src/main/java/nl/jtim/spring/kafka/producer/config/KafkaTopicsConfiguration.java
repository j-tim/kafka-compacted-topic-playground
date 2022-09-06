package nl.jtim.spring.kafka.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static java.lang.String.valueOf;
import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * https://docs.confluent.io/platform/current/installation/configuration/topic-configs.html
 */
@Configuration
public class KafkaTopicsConfiguration {

    public final static String TOPIC_NAME = "messages-topic";
    public final static String UNBOUNDED_KEYSPACE_TOPIC_NAME = "messages-compacted-unbounded-keyspace-topic";

    public final static String BOUNDED_KEYSPACE_TOPIC_NAME = "messages-compacted-bounded-keyspace-topic";

    private final static long DEFAULT_TOPIC_RETENTION_7_DAYS = DAYS.toMillis(7);

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(TOPIC_NAME)
            .config("segment.ms", valueOf(SECONDS.toMillis(30)))
            // The time to wait before deleting a file from the filesystem. Default 60000 (1 minute)
            .config("file.delete.delay.ms", valueOf(SECONDS.toMillis(30)))
            .partitions(1)
            .build();
    }

    @Bean
    public NewTopic unboundedKeyspaceCompactedTopic() {
        return TopicBuilder.name(UNBOUNDED_KEYSPACE_TOPIC_NAME)
            .compact()
            .config("segment.ms", valueOf(SECONDS.toMillis(30)))
            // The time to wait before deleting a file from the filesystem. Default 60000 (1 minute)
            .config("file.delete.delay.ms", valueOf(SECONDS.toMillis(30)))
            .partitions(1)
            .build();
    }

    @Bean
    public NewTopic boundedKeyspaceCompactedTopic() {

        return TopicBuilder.name(BOUNDED_KEYSPACE_TOPIC_NAME)
            .compact()
            .config("retention.ms", valueOf(DEFAULT_TOPIC_RETENTION_7_DAYS))
            .config("segment.ms", valueOf(SECONDS.toMillis(30)))
            // The time to wait before deleting a file from the filesystem. Default 60000 (1 minute)
            .config("file.delete.delay.ms", valueOf(SECONDS.toMillis(30)))
            .partitions(1)
            .build();
    }
}
