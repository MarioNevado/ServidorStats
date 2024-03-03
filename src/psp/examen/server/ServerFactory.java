/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.examen.server;

import psp.examen.biz.ObjetoCompartido;

/**
 *
 * @author dev
 */
public class ServerFactory {
    public static void main(String[] args) {
        ObjetoCompartido oc = new ObjetoCompartido();
        ServerEmisor emisor = new ServerEmisor(oc);
        ServerReceptor receptor = new ServerReceptor(oc);
        ServerStats stats = new ServerStats(oc);
        emisor.start();
        receptor.start();
        stats.start();
    }
    
    
}
