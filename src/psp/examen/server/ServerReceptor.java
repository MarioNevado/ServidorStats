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
public class ServerReceptor extends Thread {
    
    private ObjetoCompartido oc;

    public ServerReceptor(ObjetoCompartido oc) {
        this.oc = oc;
    }

    @Override
    public void run() {
        Socket s;
        int id = 0;
        ManagerReceptor manager;
        try(ServerSocket ss = new ServerSocket(Configuration.RECIEVE_PORT)){
            do {
                id++;
                s = ss.accept();
                manager = new ManagerReceptor(s, oc, id);
                manager.start();
                
            } while (true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
