/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.examen.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import psp.examen.biz.Message;
import psp.examen.biz.ObjetoCompartido;
import psp.examen.biz.Type;
import psp.examen.tools.Utils;

/**
 *
 * @author dev
 */
public class ManagerStats extends Thread{
    private Socket costumer;
    private ObjetoCompartido oc;
    private int id;

    public ManagerStats(int id, Socket costumer, ObjetoCompartido oc) {
        this.costumer = costumer;
        this.oc = oc;
        this.id = id;
    }

    @Override
    public void run() {
        String content;
        Message m;
        ObjectInputStream ois = null;
        try {
                ois = new ObjectInputStream(costumer.getInputStream());
                m = (Message) ois.readObject();
                content = new String(Utils.descifrarClaveSimetrica(m.getMessage(), oc.getKey()));
                System.out.println(m.getType() + ": " + content);
        }catch(EOFException eof){
            System.err.println("Cierre abrupto del cliente");
            oc.removeRecipient(id);
        }catch(StreamCorruptedException stream){
            stream.printStackTrace();
            System.err.println("Error al intentar leer del inputstream");
        } catch (IOException | ClassNotFoundException io) {
            io.printStackTrace();
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
