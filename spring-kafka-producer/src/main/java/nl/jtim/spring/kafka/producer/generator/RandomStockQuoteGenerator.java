package nl.jtim.spring.kafka.producer.generator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RandomStockQuoteGenerator extends AbstractRandomStockQuoteGenerator {

    public StockQuote generate() {
        String randomInstrument = pickRandomSymbol();
        BigDecimal randomPrice = generateRandomPrice();
        return new StockQuote(randomInstrument, randomPrice.toPlainString());
    }
}
