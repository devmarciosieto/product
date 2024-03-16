package br.com.mmmsieto.product.domain.exception;

public class FallbackException extends RuntimeException{

    public FallbackException(Throwable throwable) {
        super("We are currently unavailable, please try again later.", throwable);
    }

}
