package com.lex4hex.stocks.dto;

import com.lex4hex.stocks.config.ExceptionAdvisor;
import com.lex4hex.stocks.exception.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Entity for API error responses
 *
 * @see ExceptionAdvisor
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    /**
     * Type of returned error
     */
    private ErrorType errorType;


    /**
     * Description of exception
     */
    private String errorMessage;
}
