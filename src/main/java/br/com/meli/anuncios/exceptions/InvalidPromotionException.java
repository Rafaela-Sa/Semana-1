package br.com.meli.anuncios.exceptions;

public class InvalidPromotionException extends RuntimeException {
    public InvalidPromotionException (String message) {
        super(message);
}
