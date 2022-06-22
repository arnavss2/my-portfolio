package com.google.sps.servlets;

public class Contact {
    private long id;
    private String name;
    private String email;
    private String message;

    public Contact(){
        
    }

    public Contact(long id, String name, String email, String message){
        this.id = id;
        this.name = name;
        this.email = email;
        this.message = message;
    }
}
