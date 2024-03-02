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
    private int receptor;
    private int sender;

    

    public Message(String message, Type type) {
        this.message = message;
        this.type = type;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getReceptor() {
        return receptor;
    }

    public void setReceptor(int receptor) {
        this.receptor = receptor;
    }

    public Type getType() {
        return type;
    }

    
    @Override
    public String toString() {
        return  type.name()+ ": " + message;
    }
}
