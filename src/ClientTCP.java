import java.io.*; 
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ClientTCP {

	private Socket maSocket;
	private PrintStream os;
	private BufferedReader is;
	//Constructeur de la classe

    public static void main(String[] args) {
        new ClientTCP();
    }

	public ClientTCP() {
        try {
            maSocket = new Socket("localhost", 4444);
            try {
                os = new PrintStream(maSocket.getOutputStream());
                String monTexte;
                is = new BufferedReader(new InputStreamReader(System.in));
                while ((monTexte = is.readLine()) != null) {
                    os.println(monTexte);
                }
            }
            catch (IOException e) {
                System.err.println(e);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
