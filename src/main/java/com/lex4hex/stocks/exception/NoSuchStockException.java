package com.lex4hex.stocks.exception;

public class NoSuchStockException extends StockException {
    public NoSuchStockException() {
        super(ErrorType.NO_STOCK_FOUND);
    }
}
