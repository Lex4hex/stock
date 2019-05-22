package com.lex4hex.stocks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lex4hex.stocks.model.Stock;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO for {@link Stock} creation and modification
 *
 * @see Stock
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Data holder for stock creation and modification")
public class EditStock {

    /**
     * {@link Stock}'s name
     */
    @ApiModelProperty(value = "Name of the stock", required = true)
    @NotNull
    private String stockName;

    /**
     * {@link Stock}'s price
     */
    @ApiModelProperty(value = "Price of the stock", required = true)
    @NotNull
    private BigDecimal stockPrice;
}
