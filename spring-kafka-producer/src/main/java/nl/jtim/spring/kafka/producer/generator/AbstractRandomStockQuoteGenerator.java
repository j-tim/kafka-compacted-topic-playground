package nl.jtim.spring.kafka.producer.generator;

import org.apache.commons.math3.random.RandomDataGenerator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class AbstractRandomStockQuoteGenerator {

    private final List<String> stockSymbols = Arrays.asList("AMZN", "GOOGL", "NFLX", "INGA", "AD", "RDSA", "KO");

    BigDecimal generateRandomPrice() {
        double leftLimit = 1.000D;
        double rightLimit = 3000.000D;

        BigDecimal randomPrice = BigDecimal.valueOf(new RandomDataGenerator().nextUniform(leftLimit, rightLimit));
        randomPrice = randomPrice.setScale(3, RoundingMode.HALF_UP);
        return randomPrice;
    }

    String pickRandomSymbol() {
        int randomIndex = new Random().nextInt(stockSymbols.size());
        return stockSymbols.get(randomIndex);
    }
}
