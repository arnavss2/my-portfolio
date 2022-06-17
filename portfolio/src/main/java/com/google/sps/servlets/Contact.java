package com.google.sps.servlets;

public class Contact {
    private long id;
    private String name;
    private String email;
    private String message;

    public Contact(){
        
    }

    public Contact(long set_id, String set_name, String set_email, String set_message){
        id = set_id;
        name = set_name;
        email = set_email;
        message = set_message;
    }
}
