/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.examen.biz;

import java.io.Serializable;

/**
 *
 * @author dev
 */
public class Message implements Serializable{
    private String message;
    private Type type;
    private int recipient;
    private int dispatcher;

    

    public Message(String message, Type type) {
        this.message = message;
        this.type = type;
    }

    public int getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(int dispatcher) {
        this.dispatcher = dispatcher;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public Type getType() {
        return type;
    }

    
    @Override
    public String toString() {
        return  type.name()+ ": " + message;
    }
}
