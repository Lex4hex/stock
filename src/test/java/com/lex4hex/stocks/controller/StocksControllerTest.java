package com.lex4hex.stocks.controller;

import com.google.common.collect.ImmutableList;
import com.lex4hex.stocks.dto.EditStock;
import com.lex4hex.stocks.exception.NoSuchStockException;
import com.lex4hex.stocks.mapper.ResponseMapper;
import com.lex4hex.stocks.model.Stock;
import com.lex4hex.stocks.service.StockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class StocksControllerTest {

    @Captor
    ArgumentCaptor<EditStock> captor;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StockService stockService;

    @MockBean
    private ResponseMapper responseMapper;

    @BeforeEach
    void init() {
        when(responseMapper.mapResponse(any())).thenCallRealMethod();
        when(responseMapper.mapResponseList(any())).thenCallRealMethod();
    }

    @Test
    void getStocks() throws Exception {
        final Stock stock = new Stock();
        final String name = "Payconic";
        stock.setName(name);
        stock.setId(UUID.randomUUID());
        when(stockService.getAllStocks()).thenReturn(ImmutableList.of(stock));

        mockMvc.perform(get("/api/stocks/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stockName", is(name)))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getStock() throws Exception {
        final Stock stock = new Stock();
        final UUID id = UUID.randomUUID();
        stock.setId(id);
        when(stockService.getStockById(eq(id))).thenReturn(stock);

        mockMvc.perform(get("/api/stocks/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id.toString())));
    }

    @Test
    void getStock404() throws Exception {
        final UUID id = UUID.randomUUID();
        when(stockService.getStockById(id)).thenThrow(NoSuchStockException.class);

        mockMvc.perform(get("/api/stocks/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void createStock() throws Exception {
        final String request = "{\"stockName\":\"test\", \"stockPrice\":\"123\"}";

        mockMvc.perform(post("/api/stocks/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.getBytes()))
                .andExpect(status().isCreated());

        verify(stockService).createStock(captor.capture());

        final EditStock stockDTO = captor.getValue();
        Assertions.assertEquals("test", stockDTO.getStockName());
        Assertions.assertEquals(BigDecimal.valueOf(123), stockDTO.getStockPrice());
    }

    @Test
    void createStockValidates() throws Exception {
        final String request = "{\"stockName\":\"test\", \"stockPrice\":\"null\"}";

        mockMvc.perform(post("/api/stocks/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.getBytes()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateStock() throws Exception {
        final UUID id = UUID.randomUUID();
        final String request = "{\"stockName\":\"test\", \"stockPrice\":\"123\"}";

        final EditStock editStock = new EditStock();
        editStock.setStockName("test");
        editStock.setStockPrice(BigDecimal.valueOf(123));

        mockMvc.perform(put("/api/stocks/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.getBytes()))
                .andExpect(status().isOk());

        verify(stockService).updateStockPrice(eq(id), eq(editStock));
    }
}
