package com.lex4hex.stocks.controller;

import com.lex4hex.stocks.dto.EditStock;
import com.lex4hex.stocks.dto.StockResponse;
import com.lex4hex.stocks.mapper.ResponseMapper;
import com.lex4hex.stocks.model.Stock;
import com.lex4hex.stocks.service.StockService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * API controller for operation with {@link Stock}s
 */
@RestController
@RequestMapping("/api/stocks")
@AllArgsConstructor
public class StocksController {

    private StockService stockService;
    private ResponseMapper responseMapper;

    @GetMapping
    @ApiOperation("Returns all stocks from repository")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Unexpected server error")
    })
    public List<StockResponse> getStocks() {
        return responseMapper.mapResponseList(stockService.getAllStocks());
    }

    @GetMapping("/{stockId}")
    @ApiOperation("Returns stock by it's unique id")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Unexpected server error"),
            @ApiResponse(code = 404, message = "No such stock")
    })
    public StockResponse getStock(@PathVariable @NotNull UUID stockId) {
        return responseMapper.mapResponse(stockService.getStockById(stockId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a new stock with provided data")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Unexpected server error"),
    })
    public void createStock(@RequestBody @Valid @NotNull EditStock stock) {
        stockService.createStock(stock);
    }

    @PutMapping("/{stockId}")
    @ApiOperation("Updates a stock with new price")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Unexpected server error"),
    })
    public void updateStock(@PathVariable @NotNull UUID stockId, @RequestBody @Valid @NotNull EditStock updatedStock) {
        stockService.updateStockPrice(stockId, updatedStock);
    }
}
