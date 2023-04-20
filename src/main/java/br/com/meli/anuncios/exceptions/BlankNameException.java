package br.com.meli.anuncios.exceptions;

public class BlankNameException extends RuntimeException {
    public BlankNameException(String message) {
        super(message);
    }

}
