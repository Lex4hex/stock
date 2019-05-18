package com.lex4hex.stocks.service;

import com.lex4hex.stocks.dto.EditStock;
import com.lex4hex.stocks.exception.ErrorType;
import com.lex4hex.stocks.exception.StockException;
import com.lex4hex.stocks.model.Stock;
import com.lex4hex.stocks.repository.StockRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Provides basic logic for working with {@link Stock}s
 */
@Service
@AllArgsConstructor
@Slf4j
public class StockService {

    private StockRepository stockRepository;

    /**
     * Finds and returns all {@link Stock} objects
     *
     * @return All persisted {@link Stock} objects
     */
    public List<Stock> getAllStocks() {
        return stockRepository.getAllStocks();
    }

    public void createStock(EditStock stockDTO) {
        final Stock stock = new Stock();

        stock.setLastUpdate(LocalDateTime.now());
        stock.setCurrentPrice(stockDTO.getStockPrice());
        stock.setName(stockDTO.getStockName());
        stock.setId(UUID.randomUUID());

        stockRepository.saveStock(stock);
        log.info("Created a new stock with id = {}, name = {} and price = {}", stock.getId(), stock.getName(),
                stock.getCurrentPrice());
    }

    /**
     * Finds a {@link Stock} with provided id
     *
     * @param stockId Unique {@link Stock} id
     * @return Found {@link Stock}
     */
    public Stock getStockById(UUID stockId) {
        return stockRepository.getStockById(stockId);
    }

    /**
     * Updates a {@link Stock} by id with provided price.
     *
     * @param stockId      Unique {@link Stock} id
     * @param updatedStock Updated {@link Stock} data
     */
    public void updateStockPrice(UUID stockId, EditStock updatedStock) {
        if (updatedStock.getStockPrice().compareTo(BigDecimal.ZERO) < 1) {
            throw new StockException(ErrorType.WRONG_PRICE);
        }

        final Stock stock = stockRepository.getStockById(stockId);
        stock.setCurrentPrice(updatedStock.getStockPrice());
        stock.setName(updatedStock.getStockName());
        stock.setLastUpdate(LocalDateTime.now());
        log.info("Updated stock with id = {} and name = {} with new price = {}", stock.getId(), stock.getName(),
                stock.getCurrentPrice());
    }
}
