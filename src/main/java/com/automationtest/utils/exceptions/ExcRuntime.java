package com.automationtest.utils.exceptions;

public class ExcRuntime extends RuntimeException{

    public ExcRuntime(){
        super();
    }

    public ExcRuntime(String message){
        super(message);
    }

    public ExcRuntime(String message,Throwable cause){
        super(message,cause);
    }
}
