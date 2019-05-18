package com.lex4hex.stocks.repository;

import com.lex4hex.stocks.exception.NoSuchStockException;
import com.lex4hex.stocks.model.Stock;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StockRepositoryTest {

    private StockRepository uut = new StockRepository();

    @Test
    void getStockById() {
        final Stock stock = new Stock();
        stock.setId(UUID.randomUUID());
        uut.saveStock(stock);

        final Stock foundStock = uut.getStockById(stock.getId());

        assertEquals(stock, foundStock);
    }

    @Test
    void getStockByIdThrows() {
        assertThrows(NoSuchStockException.class, () -> uut.getStockById(UUID.randomUUID()));
    }

    @Test
    void saveStock() {
        final Stock stock = new Stock();
        stock.setId(UUID.randomUUID());

        uut.saveStock(stock);

        final Stock savedStock = uut.getStockById(stock.getId());

        assertEquals(savedStock, stock);
    }

    @Test
    @SuppressWarnings("unchecked")
    void getAllStocks() {
        final Stock stock1 = new Stock();
        stock1.setId(UUID.randomUUID());
        final Stock stock2 = new Stock();
        stock2.setId(UUID.randomUUID());

        uut.saveStock(stock1);
        uut.saveStock(stock2);

        final List<Stock> allStocks = uut.getAllStocks();

        Assert.assertThat(allStocks, hasItems(
                hasProperty("id", CoreMatchers.is(stock1.getId())),
                hasProperty("id", CoreMatchers.is(stock2.getId()))
        ));
    }
}
