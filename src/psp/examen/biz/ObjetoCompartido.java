/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.examen.biz;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author dev
 */
public class ObjetoCompartido {

    Map<Integer, Socket> costumers = new LinkedHashMap<>();
    Map<Integer, Socket> corruptedCostumers = new LinkedHashMap<>();
    Map<Integer, Integer> sentMessages = new LinkedHashMap<>();
    int messages = 0;

    public synchronized void addCostumer(int id, Socket costumer) {
        this.costumers.put(id, costumer);
    }

    public synchronized void removeCostumer(int id) {
        this.costumers.remove(id);
    }

    public synchronized void sendMessage(Message message) {
        ObjectOutputStream oos;
        for (Map.Entry<Integer, Socket> entry : costumers.entrySet()) {
            int id = entry.getKey();
            Socket costumer = entry.getValue();
            try {
                oos = new ObjectOutputStream(costumer.getOutputStream());
                oos.writeObject(message);
                messages++;
                if (sentMessages.containsKey(id)) {
                    sentMessages.put(id, sentMessages.get(id) + 1);
                }else{
                    sentMessages.put(id, 1);
                }
            } catch (IOException io) {
                corruptedCostumers.put(id, costumer);
            }
        }
        for (int id : corruptedCostumers.keySet()) {
            this.removeCostumer(id);
        }
    }
    
    public synchronized void sendStats(){
        
    }

    public synchronized void sendPrivateMsg(Message m) {
        ObjectOutputStream oos;
        try {
            if (this.costumers.containsKey(m.getReceptor())) {
                oos = new ObjectOutputStream(this.costumers.get(m.getReceptor()).getOutputStream());
                oos.writeObject(m);
                messages++;
                if (sentMessages.containsKey(m.getSender())) {
                    sentMessages.put(m.getSender(), sentMessages.get(m.getSender()) + 1);
                }else{
                    sentMessages.put(m.getSender(), 1);
                }
                if (messages >= 10) {
                    
                }
            } else {
                System.err.println("Usuario no encontrado");
            }
        } catch (IOException ioex) {
            System.err.println("Error");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
