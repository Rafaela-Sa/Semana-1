package br.com.meli.anuncios.exceptions;

public class InvalidPriceException extends RuntimeException {
    public InvalidPriceException (String message) {
        super(message);
}
