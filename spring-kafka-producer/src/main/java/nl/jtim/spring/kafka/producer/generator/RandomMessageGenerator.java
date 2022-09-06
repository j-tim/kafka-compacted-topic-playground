package nl.jtim.spring.kafka.producer.generator;

import com.github.javafaker.Faker;
import com.github.javafaker.GameOfThrones;
import org.springframework.stereotype.Component;

@Component
public class RandomMessageGenerator {

    private final Faker faker;

    public RandomMessageGenerator() {
        faker = new Faker();
    }

    public Message random() {
        GameOfThrones gameOfThrones = faker.gameOfThrones();

        return new Message(gameOfThrones.character().toLowerCase(), gameOfThrones.quote());
    }
}
