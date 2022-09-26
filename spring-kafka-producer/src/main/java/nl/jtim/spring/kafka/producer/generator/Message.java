package nl.jtim.spring.kafka.producer.generator;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class Message {
    private final String character;
    private final String quote;

    public Message(String character, String quote) {
        this.character = character;
        this.quote = quote;
    }

    public String getKey() {
        String key = "------------------------------------";
        String characterWithoutSpaces = character.replaceAll(" ", "-");
        String substring = key.substring(character.length() -1, key.length() -1);

        return characterWithoutSpaces + substring;
    }
}
