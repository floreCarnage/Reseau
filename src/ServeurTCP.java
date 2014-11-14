import java.io.*;
import java.net.*;
import java.util.Date;

/**
 * La classe ServeurTCP, qui implémente le code du serveur TCP. 
 * La sérialisation se fait en chaîne
 * Un main qui execute le serveur, dont le main contient juste l'attente d'une connexion, 
 * et quand il reçoit des messages, il répond. 
 * @author garance & Jo
 *
 */
public class ServeurTCP {

    private ServerSocket maSocket;

    public static void main(String[] args) {
        new ServeurTCP();
    }

    @SuppressWarnings("deprecation")
    public ServeurTCP() {
        try {
            maSocket = new ServerSocket(4444);
            System.out.println("Le serveur est allume : " + new Date());
            while (true) {
                //On attend une demande de connexion d'un client
                Socket clientsocket = maSocket.accept();
                System.out.println("J'ai reçu une connexion !");
                try {
                    PrintStream sortie = new PrintStream(clientsocket.getOutputStream());
                    BufferedReader entree = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
                    while (true) {
                        String in = entree.readLine();
                        if (in == null) break;
                        //Pour le moment, on répète seulement ce qu'on a lu, donc :
                        sortie.println(in);
                    }
                } catch(IOException exception) {
                    System.out.println("Erreur: " + exception);
                }
            }
        } catch(IOException exception) {
            System.out.println("Erreur: " + exception);
        }
    }

}
