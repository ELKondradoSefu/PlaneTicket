package com.user.planeapp.exceptions;

public class EmptyFieldException extends Exception{

    public EmptyFieldException() {
        super("All fields are required!");
    }

}
