package com.lex4hex.stocks.config;

import com.lex4hex.stocks.dto.ErrorResponse;
import com.lex4hex.stocks.exception.ErrorType;
import com.lex4hex.stocks.exception.NoSuchStockException;
import com.lex4hex.stocks.exception.StockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

/**
 * Describes handling logic of exceptions that could be thrown from controller
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvisor {

    @ExceptionHandler(StockException.class)
    public final ResponseEntity handleStockException(final StockException e) {
        log.error(e.getLocalizedMessage(), e);

        return badRequest().body(new ErrorResponse(e.getErrorType(), e.getLocalizedMessage()));
    }

    @ExceptionHandler(NoSuchStockException.class)
    public final ResponseEntity handleStockException(final NoSuchStockException e) {
        log.error(e.getLocalizedMessage(), e);

        return status(NOT_FOUND).body(new ErrorResponse(e.getErrorType(), e.getLocalizedMessage()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public final ResponseEntity handleValidation(final Exception e) {
        log.error(e.getLocalizedMessage(), e);

        return badRequest().body(new ErrorResponse(ErrorType.VALIDATION_ERROR, e.getLocalizedMessage()));
    }
}
