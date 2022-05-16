package com.ibm.academy.apirest.exceptions;

public class BadRequestException extends RuntimeException
{
    public BadRequestException(String message)
    {
        super(message);
    }
    private static final long serialVersionUID = -4841429886691503204L;
}
