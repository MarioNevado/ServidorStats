/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.examen.server;

import java.net.ServerSocket;
import java.net.Socket;
import psp.examen.biz.ObjetoCompartido;
import psp.examen.tools.Configuration;

/**
 *
 * @author dev
 */
public class ServerEmisor extends Thread{
    
    private ObjetoCompartido oc;

    public ServerEmisor(ObjetoCompartido oc) {
        this.oc = oc;
    }
    
    @Override
    public void run(){
        Socket s;
        ManagerEmisor manager;
        int id = 0;
        try(ServerSocket ss = new ServerSocket(Configuration.SEND_PORT)){
            do {
                id++;
                s = ss.accept();
                manager = new ManagerEmisor(id, s, oc);
                manager.start();
            } while (true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
