package nl.jtim.spring.kafka.producer.rest;

import nl.jtim.spring.kafka.producer.generator.StockQuote;
import nl.jtim.spring.kafka.producer.generator.StockQuoteProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestResource {

    private final StockQuoteProducer stockQuoteProducer;

    public RestResource(StockQuoteProducer stockQuoteProducer) {
        this.stockQuoteProducer = stockQuoteProducer;
    }

    @PostMapping
    public void produce(@RequestBody StockQuote stockQuote) {
        stockQuoteProducer.produce(stockQuote);
    }
}
