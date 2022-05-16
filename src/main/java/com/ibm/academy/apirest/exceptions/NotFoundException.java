package com.ibm.academy.apirest.exceptions;

public class NotFoundException extends BadRequestException
{
    public NotFoundException(String message) {
        super(message);
    }
    private static final long serialVersionUID = -7004392948178062653L;
}
