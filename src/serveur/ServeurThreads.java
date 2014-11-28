package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.Socket;


public class ServeurThreads implements Runnable{

    private Socket clientSocket;
    private Inet4Address adresse;
    
    public ServeurThreads(Socket socket) {
        clientSocket = socket;
        adresse = (Inet4Address) socket.getInetAddress();
    }
    
    @Override
    public void run() {
        try {
            PrintStream sortie = new PrintStream(
                    clientSocket.getOutputStream());
            BufferedReader entree = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            System.err.println("Le client "+adresse+" s'est connecté");
            while (true) {
                String in = entree.readLine();
                if (in == null) {
                    System.err.println("Le client "+adresse+" s'est déconnecté");
                    sortie.close();
                    entree.close();
                    clientSocket.close();
                    break; 
                }
                   
                // Pour le moment, on répète seulement ce qu'on a lu,
                // donc :
                sortie.println(in);
            }
            
        } catch (IOException exception) {
            System.out.println("Erreur: " + exception);
        }
        
    }

}
