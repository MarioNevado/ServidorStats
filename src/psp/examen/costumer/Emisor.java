/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.examen.costumer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import psp.examen.biz.Message;
import psp.examen.biz.Type;
import psp.examen.tools.Configuration;

/**
 *
 * @author dev
 */
public class Emisor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ObjectOutputStream oos = null;
        String string;
        Message message;
        try (Socket s = new Socket(Configuration.HOST, Configuration.SEND_PORT)) {
            do {
                System.out.print("Introducir mensaje: ");
                string = sc.nextLine();
                oos = new ObjectOutputStream(s.getOutputStream());
                if (string.startsWith(".private")) {
                    message = new Message(string.split(" ", 3)[2], Type.PRIVATE);
                    try{
                        message.setReceptor(Integer.parseInt(string.split(" ", 3)[1]));
                    }catch(NumberFormatException nbe){
                        System.err.println("No es un número");
                        message.setReceptor(0);
                    }
                }else{
                    message = new Message(string, Type.PUBLIC);
                }
                oos.writeObject(message);
            } while (!string.equals("*"));
            System.out.println("Adiós...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    System.err.println("Error en el outputstream");
                }
            }
        }
    }

}
