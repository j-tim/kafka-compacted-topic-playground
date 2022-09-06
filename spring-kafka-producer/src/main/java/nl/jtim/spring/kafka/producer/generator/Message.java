package nl.jtim.spring.kafka.producer.generator;

import lombok.Getter;

@Getter
public class Message {
    private final String character;
    private final String quote;

    public Message(String character, String quote) {
        this.character = character;
        this.quote = quote;
    }

    public String getKey() {
        return character.replaceAll(" ", "-");
    }
}
