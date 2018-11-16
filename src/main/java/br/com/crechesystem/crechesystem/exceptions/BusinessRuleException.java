package br.com.crechesystem.crechesystem.exceptions;

public class BusinessRuleException extends Exception{
    private static final long serialVersionUID = 1L;

    public BusinessRuleException(String message) {
        super(message);
    }
}
