package nl.jtim.spring.kafka.producer.generator;

public interface MessageProducer {

    void produce(Message message);
}
