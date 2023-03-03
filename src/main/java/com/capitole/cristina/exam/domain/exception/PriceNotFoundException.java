package com.capitole.cristina.exam.domain.exception;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException() {
        super("Price not found");
    }
}
