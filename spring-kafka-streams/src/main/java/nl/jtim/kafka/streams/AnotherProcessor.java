package nl.jtim.kafka.streams;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static nl.jtim.kafka.streams.config.KafkaTopicsConfiguration.STOCK_QUOTES_COMPACT_DELETE_TOPIC_NAME;
import static nl.jtim.kafka.streams.config.KafkaTopicsConfiguration.STOCK_QUOTES_TOPIC_NAME;

@Component
public class AnotherProcessor {

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder.stream(STOCK_QUOTES_TOPIC_NAME);
        stream.to(STOCK_QUOTES_COMPACT_DELETE_TOPIC_NAME);
    }
}
