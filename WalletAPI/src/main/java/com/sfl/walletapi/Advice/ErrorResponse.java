package com.sfl.walletapi.Advice;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private int statusCode;
    private String errorType;
    private String errorMessage;
    private LocalDateTime timestamp;
}
