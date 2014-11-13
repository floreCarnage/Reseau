import java.io.*;
import java.net.*;

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
	private PrintStream os;
	private DataInputStream is;
	//Cosntructeur de la classe
	public ServeurTCP() {
		Socket maSocket = null;
		//Outstream déclaré avant le instream pour éviter les bugs
		 os = null;
		 is = null;
		
	}
	//Le main du côté serveur. 
	public static void main(String[] args) {
		ServeurTCP monServeur = new ServeurTCP();
		String line;
		try { echoServer = new ServerSocket(9999);}
		catch (IOException e) {System.out.println(e); }
		try {
			clientSocket = echoServer.accept();
			is = new DataInputStream(clientSocket.getInputStream());
			os = new PrintStream(clientSocket.getOutputStream());
			while (true) {
				line = is.readLine();
				os.println(line);
			}
		}
		catch (IOException e) {
			System.out.println(e);}
	} 
	
	public void setSocket(ServerSocket socket) {
		maSocket = socket;
	}
	
	public void setOS(PrintStream printStream) {
		os = printStream;
	}
	public void setIS(DataInputStream dataInputStream) {
		is = dataInputStream;
	}
	
	
	public ServerSocket getMaSocket() {
		return maSocket;
	}
	public PrintStream getOs() {
		return os;
	}
	public DataInputStream getIs() {
		return is;
	}
	
	public boolean communication() {
		if (maSocket != null && os != null && is != null) {
			os.print("Bonjoor\n");
			return true;
		}
		return false;
	}
}
