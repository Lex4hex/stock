package com.lex4hex.stocks.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorType {
    WRONG_PRICE("Wrong price"),
    NO_STOCK_FOUND("No such stock"),
    VALIDATION_ERROR("Validation error");

    private String message;

    public String getMessage() {
        return message;
    }
}
