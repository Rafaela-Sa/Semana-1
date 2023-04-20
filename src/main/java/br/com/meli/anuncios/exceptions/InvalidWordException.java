package br.com.meli.anuncios.exceptions;

public class InvalidWordException extends RuntimeException{
    public InvalidWordException (String message) {
        super(message);
    }

}
