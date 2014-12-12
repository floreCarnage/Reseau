package serveur;

import java.io.*;
import java.net.*;
import java.util.Date;

import donnees.Donnees;
import com.google.gson.Gson;

/**
 * La classe serveur.ServeurTCP, qui implémente le code du serveur TCP. La sérialisation
 * se fait en chaîne Un main qui execute le serveur, dont le main contient juste
 * l'attente d'une connexion, et quand il reçoit des messages, il répond.
 * 
 * @author garance & Jo
 * 
 */
public class ServeurTCP {

    private ServerSocket maSocket;
    private Donnees donnee;

    public static void main(String[] args) {
        new ServeurTCP();
    }
    @SuppressWarnings("deprecation")
    public ServeurTCP() {
        try {
            donnee = new Donnees();
            maSocket = new ServerSocket(4444);
            System.out.println("Le serveur est allume : " + new Date());
            System.out.println("je sui2");
            while (true) {
                // On attend une demande de connexion d'un client
                System.out.println("Je vais accepter une socket");
                Socket clientSocket = maSocket.accept();
                System.out.println("Je vais accepter une socket");
                System.out.println("je suis dans le while");
                System.out.println("Je crée une thread");
                ServeurThreads servThread = new ServeurThreads(clientSocket);
                System.out.println("J'ai créé une thread, je la start");
                new Thread(servThread).start();
                System.out.println("méthode start du serveur fini");
            }
        } catch (IOException exception) {
            System.out.println("Erreur: " + exception);
        }
        fermeture(maSocket);
    }
    
    private void fermeture(ServerSocket socket) {
        try {
            socket.close();
        } catch (Exception e) {
            System.err.println("Erreur ce port disparait à jamais");
        }
    }
    
    public Donnees getDonnees() {
        return donnee;
    }
}
