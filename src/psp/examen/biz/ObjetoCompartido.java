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
import java.util.logging.Level;
import java.util.logging.Logger;
import psp.examen.tools.Configuration;

/**
 *
 * @author dev
 */
public class ObjetoCompartido {

    Map<Integer, Socket> dispatchers = new LinkedHashMap<>();
    Map<Integer, Socket> recipients = new LinkedHashMap<>();
    Map<Integer, Socket> corruptedCostumers = new LinkedHashMap<>();
    Map<Integer, Integer> sentMessages = new LinkedHashMap<>();
    int messages = 0;

    public synchronized void addRecipient(int id, Socket costumer) {
        this.recipients.put(id, costumer);
    }

    public synchronized void removeRecipient(int id) {
        this.recipients.remove(id);
    }

    public synchronized void addDispatcher(int id, Socket costumer) {
        this.dispatchers.put(id, costumer);
    }

    public synchronized void removeDispatcher(int id) {
        this.dispatchers.remove(id);
    }

    public synchronized void sendMessage(Message message) {
        ObjectOutputStream oos;
        for (Map.Entry<Integer, Socket> entry : recipients.entrySet()) {
            int id = entry.getKey();
            Socket costumer = entry.getValue();
            try {
                oos = new ObjectOutputStream(costumer.getOutputStream());
                oos.writeObject(message);
            } catch (IOException io) {
                corruptedCostumers.put(id, costumer);
            }
        }
        if (sentMessages.containsKey(message.getDispatcher())) {
            sentMessages.put(message.getDispatcher(), sentMessages.get(message.getDispatcher()) + 1);
        } else {
            sentMessages.put(message.getDispatcher(), 1);
        }
        messages++;
        if (messages >= 10) {
            sendStats();
            messages = 0;
        }
        for (int id : corruptedCostumers.keySet()) {
            this.removeRecipient(id);
        }

    }

    public synchronized void sendStats() {
        String content;
        ObjectOutputStream oos = null;
        try (Socket socket = new Socket(Configuration.HOST, Configuration.RECIEVE_STATS)) {
            
            content = "Receptores conectados: \n";
            for (Integer integer : recipients.keySet()) {
                content += integer + "\n";
            }
            content += "Emisores conectados: \n";
            for (Integer integer : dispatchers.keySet()) {
                content += integer + "\n";
            }
            content += "Mensajes enviados a cada cliente: \n";
            for (Map.Entry<Integer, Integer> entry : sentMessages.entrySet()) {
                int key = entry.getKey();
                int val = entry.getValue();
                content += key + ": " + val + " mensajes \n";
            }
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(new Message(content, Type.PUBLIC));
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    System.err.println("Error cerrando outputstream");
                }
            }
        }
    }

    public synchronized void sendPrivateMsg(Message m) {
        ObjectOutputStream oos;
        try {
            if (this.recipients.containsKey(m.getRecipient())) {
                oos = new ObjectOutputStream(this.recipients.get(m.getRecipient()).getOutputStream());
                oos.writeObject(m);
                messages++;
                if (sentMessages.containsKey(m.getDispatcher())) {
                    sentMessages.put(m.getDispatcher(), sentMessages.get(m.getDispatcher()) + 1);
                } else {
                    sentMessages.put(m.getDispatcher(), 1);
                }
                if (messages >= 10) {
                    sendStats();
                    messages = 0;
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
