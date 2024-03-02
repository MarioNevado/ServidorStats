/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.examen.costumer;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import psp.examen.biz.Message;
import psp.examen.tools.Configuration;

/**
 *
 * @author dev
 */
public class Receptor {
    public static void main(String[] args) {
        ObjectInputStream ois = null;
        Message m;
        try(Socket s = new Socket(Configuration.HOST, Configuration.RECIEVE_PORT)){
            do {
                ois = new ObjectInputStream(s.getInputStream());
                m = (Message) ois.readObject();
                System.out.println(m);
            } while (true);
        }catch(EOFException eof){
            System.err.println("Servidor caído");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.err.println("Error cerrando inputstream");
                }
            }
        }
    }
}
