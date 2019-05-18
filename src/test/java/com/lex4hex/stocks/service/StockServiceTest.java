package com.lex4hex.stocks.service;

import com.google.common.collect.ImmutableList;
import com.lex4hex.stocks.dto.EditStock;
import com.lex4hex.stocks.exception.StockException;
import com.lex4hex.stocks.model.Stock;
import com.lex4hex.stocks.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StockServiceTest {

    @InjectMocks
    private StockService uut;

    @Mock
    private StockRepository stockRepository;

    @Captor
    ArgumentCaptor<Stock> captor;


    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllStocks() {
        when(stockRepository.getAllStocks()).thenReturn(ImmutableList.of(new Stock()));

        final List<Stock> allStocks = uut.getAllStocks();

        assertEquals(1, allStocks.size());
    }

    @Test
    void createStock() {
        final EditStock editStock = new EditStock();
        final String name = "testStock";
        editStock.setStockName(name);
        editStock.setStockPrice(BigDecimal.ONE);

        uut.createStock(editStock);

        verify(stockRepository).saveStock(captor.capture());
        assertEquals(BigDecimal.ONE, captor.getValue().getCurrentPrice());
        assertEquals(name, captor.getValue().getName());
    }

    @Test
    void getStockById() {
        final UUID id = UUID.randomUUID();

        final Stock stock = new Stock();
        when(stockRepository.getStockById(eq(id))).thenReturn(stock);

        final Stock stockById = uut.getStockById(id);
        verify(stockRepository).getStockById(eq(id));

        assertEquals(stock, stockById);
    }

    @Test
    void updateStockPrice() {
        final UUID id = UUID.randomUUID();
        final Stock stock = new Stock();

        final EditStock editStock = new EditStock();
        editStock.setStockPrice(BigDecimal.ONE);

        when(stockRepository.getStockById(id)).thenReturn(stock);

        uut.updateStockPrice(id, editStock);

        assertEquals(BigDecimal.ONE, stock.getCurrentPrice());
    }

    @Test
    void updateStockPriceThrows() {
        final EditStock editStock = new EditStock();
        editStock.setStockPrice(BigDecimal.valueOf(-1));

        assertThrows(StockException.class, () -> uut.updateStockPrice(UUID.randomUUID(), editStock));
    }
}
