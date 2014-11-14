import java.io.*; 
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ClientTCP {

	private Socket maSocket;
    private PrintWriter out;
    private BufferedReader in;
	private BufferedReader stdIn;
	//Constructeur de la classe

    public static void main(String[] args) {
        new ClientTCP();
    }

	@SuppressWarnings("deprecation")
    public ClientTCP() {
        try {
            //Connexion à notre serveur, en local
            maSocket = new Socket("10.212.110.223", 4444);

            System.out.println("Je suis connecté en local au serveur, maintenant je vais ouvrir les output et input");
            //Ouverture d'un output pour le client sur la socket de communication
            out = new PrintWriter(maSocket.getOutputStream(), true);
            //Ouvrir un reader pour le client sur la socket de communication
            in = new BufferedReader(new InputStreamReader(maSocket.getInputStream()));
            //On utiliser ce buffer pour récupérer ce qu'on dit sur le terminal (System.in)
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("In et out ouverts");
            activerEcho();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    //Méthode pour envoyer des messages au serveur
    public void activerEcho() {
        String userInput;
        try {
            //On lit une ligne du terminal
            while ((userInput = stdIn.readLine()) != null) {
                //On envoie cette ligne immédiatement au serveur, avec le canal out de la socket
                out.println(userInput);
                //Ici, on lit l'entrée qui vient de la socket, donc on reçoit quelque chose ssi le serveur a renvoyé des informations
                System.out.println("echo: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
