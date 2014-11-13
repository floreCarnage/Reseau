import java.io.*; 
import java.net.*;

public class ClientTCP {

	private Socket maSocket;
	private PrintStream os;
	private DataInputStream is;
	//Cosntructeur de la classe
	public ClientTCP() {
		Socket maSocket = null;
		//Outstream déclaré avant le instream pour éviter les bugs
		 os = null;
		 is = null;
		
	}
	public void setSocket(Socket socket) {
		maSocket = socket;
	}
	
	public void setOS(PrintStream printStream) {
		os = printStream;
	}
	public void setIS(DataInputStream dataInputStream) {
		is = dataInputStream;
	}
	public Socket getSocket() {
		return maSocket;
	}

	public DataInputStream getIs() {
		return is;
	}
	public PrintStream getOs() {
		return os;
	}

	public void connexionClient(ServeurTCP serveur) {
		try {
			maSocket = serveur.getMaSocket().accept();
			is = serveur.getIs();
			os = serveur.getOs();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
