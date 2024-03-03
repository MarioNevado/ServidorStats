/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.examen.server;

import psp.examen.biz.ObjetoCompartido;
import psp.examen.tools.Configuration;
import psp.examen.tools.Utils;

/**
 *
 * @author dev
 */
public class ServerFactory {
    public static void main(String[] args) {
        ObjetoCompartido oc = new ObjetoCompartido(Utils.getKey(Configuration.SIMMETRIC_KEY_FILE));
        ServerEmisor emisor = new ServerEmisor(oc);
        ServerReceptor receptor = new ServerReceptor(oc);
        ServerStats stats = new ServerStats(oc);
        emisor.start();
        receptor.start();
        stats.start();
    }
    
    
}
