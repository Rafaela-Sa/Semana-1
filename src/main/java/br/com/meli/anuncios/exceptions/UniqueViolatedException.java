package br.com.meli.anuncios.exceptions;

public class UniqueViolatedException extends RuntimeException {
    public UniqueViolatedException(String message) {
        super(message);
    }

}
