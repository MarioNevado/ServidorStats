/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.examen.tools;

/**
 *
 * @author dev
 */
public class Configuration {
    public final static String HOST = "localhost",
            DOWNLOAD_ROUTE = "/home/dev/NetBeansProjects/Criptografía/Descifrado_", 
            HASH_ALGORYTHM = "SHA-512", 
            SIMMETRIC_KEY_FILE = "simmetricKey.key",
            ASIMMETRIC_KEY_FILE = "asimmetricKey.key",
            ENCRYPTED_FILENAME = "Cifrado_file.txt",
            TRANSFORMATION ="AES/ECB/PKCS5Padding",
            SIMMETRIC_ALGORYTHM = "AES",
            ASIMMETRIC_ALGORYTHM = "RSA";
    public final static int SEND_PORT = 6666;
    public final static int RECIEVE_PORT = 6667;
    public final static int SEND_STATS = 6668;
    public final static int RECIEVE_STATS = 6669;
}
