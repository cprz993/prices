package com.capitole.cristina.exam.service.exception;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException() {
        super("Price not found");
    }
}
