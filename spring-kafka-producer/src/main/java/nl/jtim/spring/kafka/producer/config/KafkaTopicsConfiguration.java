package nl.jtim.spring.kafka.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.valueOf;
import static java.util.concurrent.TimeUnit.*;

/**
 * https://docs.confluent.io/platform/current/installation/configuration/topic-configs.html
 */
@Configuration
public class KafkaTopicsConfiguration {

    // Cleanup policy: delete
    // Keyspace: unbounded
    public final static String TOPIC_WITH_CLEANUP_POLICY_DELETE_KEYSPACE_UNBOUNDED = "topic-delete-unbounded-keyspace";

    // Cleanup policy: delete
    // Keyspace: bounded
    public final static String TOPIC_WITH_CLEANUP_POLICY_DELETE_KEYSPACE_BOUNDED = "topic-delete-bounded-keyspace";

    // Cleanup policy: compact
    // Keyspace: bounded
    public final static String TOPIC_WITH_CLEANUP_POLICY_COMPACT_KEYSPACE_BOUNDED = "topic-compact-bounded-keyspace";

    // Cleanup policy: compact
    // Keyspace: unbounded
    public final static String TOPIC_WITH_CLEANUP_POLICY_COMPACT_KEYSPACE_UNBOUNDED = "topic-compact-unbounded-keyspace";


    // Cleanup policy: compact
    // Keyspace: bounded
    public final static String TOPIC_WITH_DELETE_AND_CLEANUP_KEYSPACE_BOUNDED = "topic-delete-and-compact-bounded-keyspace";

    // Cleanup policy: compact
    // Keyspace: unbounded
    public final static String TOPIC_WITH_DELETE_AND_CLEANUP_KEYSPACE_UNBOUNDED = "topic-delete-and-compact-unbounded-keyspace";

    private final static long DEFAULT_TOPIC_RETENTION_7_DAYS = DAYS.toMillis(7);

    private final static String CUSTOM_TOPIC_RETENTION_TWO_MINUTES = valueOf(MINUTES.toMillis(2));

    // https://docs.confluent.io/platform/current/installation/configuration/topic-configs.html#topicconfigs_retention.bytes
    private final static String DEFAULT_RETENTION_BYTES = valueOf(-1);

    private final static String CUSTOM_RETENTION_100_BYTES = valueOf(100);

    Map<String, String> defaultConfig = new HashMap<>();
    {
        /*
         * This configuration controls the maximum time we will retain a log before we will discard old log segments
         * to free up space if we are using the "delete" retention policy. This represents an SLA on how soon consumers
         * must read their data. If set to -1, no time limit is applied.
         */
        defaultConfig.put("retention.ms", CUSTOM_TOPIC_RETENTION_TWO_MINUTES);
        defaultConfig.put("segment.ms", valueOf(SECONDS.toMillis(30)));
        // The time to wait before deleting a file from the filesystem. Default 60000 (1 minute)
        defaultConfig.put("file.delete.delay.ms", valueOf(SECONDS.toMillis(30)));
        defaultConfig.put("retention.bytes", DEFAULT_RETENTION_BYTES);
    }

    /**
     * Policy: delete
     * Keyspace: unbounded
     */
    @Bean
    public NewTopic topicWithCleanupPolicyDeleteUnboundedKeyspace() {
        return TopicBuilder.name(TOPIC_WITH_CLEANUP_POLICY_DELETE_KEYSPACE_UNBOUNDED)
            .configs(defaultConfig)
            .partitions(1)
            .build();
    }

    /**
     * Policy: delete
     * Keyspace: bounded
     */
    @Bean
    public NewTopic topicWithCleanupPolicyDeleteBoundedKeyspace() {

        return TopicBuilder.name(TOPIC_WITH_CLEANUP_POLICY_DELETE_KEYSPACE_BOUNDED)
                .configs(defaultConfig)
                .partitions(1)
                .build();
    }

    /**
     * Policy: compact
     * Keyspace: unbounded
     */
    @Bean
    public NewTopic topicWithCleanupPolicyCompactUnboundedKeyspace() {
        return TopicBuilder.name(TOPIC_WITH_CLEANUP_POLICY_COMPACT_KEYSPACE_UNBOUNDED)
            .compact()
            .configs(defaultConfig)
            .partitions(1)
            .build();
    }

    /**
     * Policy: compact
     * Keyspace: bounded
     */
    @Bean
    public NewTopic topicWithCleanupPolicyCompactBoundedKeyspace() {
        return TopicBuilder.name(TOPIC_WITH_CLEANUP_POLICY_COMPACT_KEYSPACE_BOUNDED)
            .compact()
            .configs(defaultConfig)
            .partitions(1)
            .build();
    }

    @Bean
    public NewTopic topicWithCleanupPolicyDeleteAndCompactUnBoundedKeyspace() {
        return TopicBuilder.name(TOPIC_WITH_DELETE_AND_CLEANUP_KEYSPACE_UNBOUNDED)
                .configs(defaultConfig)
                .config(TopicConfig.CLEANUP_POLICY_CONFIG, "compact,delete")
                .partitions(1)
                .build();
    }

    @Bean
    public NewTopic topicWithCleanupPolicyDeleteAndCompactBoundedKeyspace() {
        NewTopic build = TopicBuilder.name(TOPIC_WITH_DELETE_AND_CLEANUP_KEYSPACE_BOUNDED)
                .configs(defaultConfig)
                // Although the kafka-configs cli uses brackets for multiple cleanup policies like '[compact,delete]'
                // in code we remove the brackets otherwise the topic won't be created!a
                // Example: kafka-configs --bootstrap-server localhost:9092 --alter --entity-type topics --entity-name stock-quotes --add-config cleanup.policy='[compact,delete]'
                .config(TopicConfig.CLEANUP_POLICY_CONFIG, "compact,delete")
                .partitions(1)
                .build();
        return build;
    }
}
