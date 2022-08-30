package nl.jtim.spring.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListenerContainer;

import java.util.Collection;

@SpringBootApplication
@EnableKafka
@Slf4j
public class SpringKafkaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaConsumerApplication.class, args);
    }

    /**
     * Doesn't do anything but just there to fully understand
     * The thread naming and message listener containers.
     */
    @Bean
    public ApplicationRunner runner(KafkaListenerEndpointRegistry registry) {
        return args -> {
            Collection<MessageListenerContainer> containers = registry.getListenerContainers();

            for (MessageListenerContainer container : containers) {
                log.info("===============================================================");
                log.info("Listener id: {}", container.getListenerId());
                log.info("Bean name: {}", ((ConcurrentMessageListenerContainer) container).getBeanName());
                log.info("container properties: {}", container.getContainerProperties());
                log.info("Group id: {}", container.getGroupId());
                log.info("===============================================================");
            }
        };
    }

}
