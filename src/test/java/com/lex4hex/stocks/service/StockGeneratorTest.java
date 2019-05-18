package com.lex4hex.stocks.service;

import com.lex4hex.stocks.model.Stock;
import com.lex4hex.stocks.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class StockGeneratorTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    void generateStocks() {
        final List<Stock> allStocks = stockRepository.getAllStocks();

        assertEquals(1, allStocks.size());
    }
}
