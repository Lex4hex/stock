package com.lex4hex.stocks.exception;

import lombok.Getter;

/**
 * Generic stock business exception. Has {@link ErrorType} property to distinct error types and provide error messages
 */
public class StockException extends RuntimeException {
    @Getter
    private ErrorType errorType;

    public StockException(ErrorType errorType) {
        super(errorType.getMessage());

        this.errorType = errorType;
    }
}
