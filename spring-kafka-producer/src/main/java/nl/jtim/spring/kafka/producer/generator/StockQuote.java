package nl.jtim.spring.kafka.producer.generator;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StockQuote {
    private final String symbol;
    private final String price;

    public StockQuote(String symbol, String price) {
        this.symbol = symbol;
        this.price = price;
    }
}
