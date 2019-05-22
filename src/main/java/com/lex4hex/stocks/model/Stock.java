package com.lex4hex.stocks.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a stock. Stock has unique id, custom name with it's price.
 * Also it has lastUpdate timestamp which represents time when stock was updated or created
 */
@Data
public class Stock {

    /**
     * Unique id
     */
    private UUID id;

    /**
     * Name of the stock
     */
    private String name;

    /**
     * Price of the stock
     */
    private BigDecimal currentPrice;

    /**
     * Time when the stock was updated or created
     */
    private LocalDateTime lastUpdate;
}
