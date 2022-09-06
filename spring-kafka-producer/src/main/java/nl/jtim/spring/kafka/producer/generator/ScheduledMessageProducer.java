package nl.jtim.spring.kafka.producer.generator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
public class ScheduledMessageProducer {

    private final List<MessageProducer> messageProducers;
    private final RandomMessageGenerator messageGenerator;

    public ScheduledMessageProducer(List<MessageProducer> messageProducers, RandomMessageGenerator messageGenerator) {
        this.messageProducers = messageProducers;
        this.messageGenerator = messageGenerator;
    }


    @Scheduled(fixedRateString = "${kafka.producer.rate}")
    public void produce() {
        Message message = messageGenerator.random();

        for (MessageProducer messageProducer : messageProducers) {
            messageProducer.produce(message);
        }
    }
}
