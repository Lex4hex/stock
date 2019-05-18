package com.lex4hex.stocks.repository;

import com.lex4hex.stocks.exception.NoSuchStockException;
import com.lex4hex.stocks.model.Stock;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Repository class for persisting of {@link Stock} objects
 */
@Repository
public class StockRepository {

    /**
     * {@link Stock} storage map. Where key is {@link Stock}'s unique id
     */
    private Map<UUID, Stock> stocks = new ConcurrentHashMap<>(20);

    /**
     * Finds a {@link Stock} with provided id in repository
     *
     * @param id Unique {@link Stock} id
     * @return Found {@link Stock} from repository
     * @throws NoSuchStockException if there's no such stock
     */
    public Stock getStockById(UUID id) throws NoSuchStockException {
        if (!stocks.containsKey(id)) {
            throw new NoSuchStockException();
        }

        return stocks.get(id);
    }

    /**
     * Saves provided {@link Stock} in repository
     *
     * @param stock Stock to save
     */
    public void saveStock(Stock stock) {
        stocks.put(stock.getId(), stock);
    }

    /**
     * Gets all {@link Stock} from stocks map
     *
     * @return All {@link Stock}'s
     */
    public List<Stock> getAllStocks() {
        return new ArrayList<>(stocks.values());
    }

}
