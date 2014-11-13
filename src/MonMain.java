import java.io.*;
import java.net.*;


public class MonMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Création de la socket du serveur, qui va attendre les connexions
		
		// Création de la socket du client, avec le nom du serveur avec qui on veut communiquer, 
		//Et le numero de port de communication. 
		ServeurTCP socketServeur = new ServeurTCP();
		//On crée un serveur, & sa socket avec le numero de port. 
		try {
			socketServeur.setSocket(new ServerSocket(4444));
		}
		catch(Exception e) {
			System.err.println("Pas reussi a creer serverSocket");
		}
		
		
		
		
		//On crée un client, et sa socket, 
		//avec le nom du serveur qu'on veut contacter, et le port de contact. 
		ClientTCP socketClient = new ClientTCP();
/*		try {
			//Attention, besoin de choisir un vrai nom d'hôte ici !
			//Création d'une socket spécifique à la place de la générique. 
			socketClient.setSocket(new Socket("nomHote", 4444));
			socketClient.setOS(new PrintStream(socketClient.getSocket().getOutputStream()));
			socketClient.setIS(new DataInputStream(socketClient.getSocket().getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Ne connaît pas l'hôte monHote");
		}
		catch(IOException e) {
			System.err.println("Pas d'entrée sortie pour la connexion à : monHote");
		} */
		//Le client essaie de se connecter au serveur. 
		socketClient.connexionClient(socketServeur);
		String line;
		while (true) {
			try {
				line = socketClient.getIs().readLine();
				
				socketServeur.getOs().println(line);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
