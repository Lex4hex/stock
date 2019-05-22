package com.lex4hex.stocks.dto;

import com.lex4hex.stocks.model.Stock;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO. Represents a stock. Stock has unique id, custom name and price.
 * Also it has lastUpdate timestamp it's the time when stock was updated or created
 **/
@Data
@ApiModel(description = "Represents a stock. Stock has unique id, custom name with it's price.")
public class StockResponse {
    /**
     * Unique id
     * Generated with {@link java.util.UUID}
     */
    @ApiModelProperty(value = "Stock's unique id", required = true)
    private String id;

    /**
     * Name of the stock
     */
    @ApiModelProperty(value = "Stock's name", required = true)
    private String stockName;

    /**
     * Price of the stock
     */
    @ApiModelProperty(value = "Price of the stock", required = true)
    private BigDecimal stockPrice;

    /**
     * Time when the stock was updated or created
     */
    @ApiModelProperty(value = "Time when stock was modified", required = true)
    private LocalDateTime lastUpdate;
}
