/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.examen.server;

import java.net.Socket;
import psp.examen.biz.ObjetoCompartido;

/**
 *
 * @author dev
 */
public class ManagerReceptor extends Thread {
    private Socket costumer;
    private ObjetoCompartido oc;
    private int id;

    public ManagerReceptor(Socket costumer, ObjetoCompartido oc, int id) {
        this.costumer = costumer;
        this.oc = oc;
        this.id = id;
    } 
    @Override
    public void run(){
        oc.addCostumer(id, costumer);
    }
}

