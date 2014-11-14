import java.io.*; 
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ClientTCP {

	private Socket maSocket;
	private DataOutputStream os;
	private BufferedReader is;
	//Constructeur de la classe

    public static void main(String[] args) {
        new ClientTCP();
    }

	@SuppressWarnings("deprecation")
    public ClientTCP() {
        try {
            maSocket = new Socket("localhost", 4444);
            try {
                os = new DataOutputStream(maSocket.getOutputStream());
                String monTexte;
                is = new BufferedReader(new InputStreamReader(maSocket.getInputStream()));
                while ((monTexte = is.readLine()) != null) {
                    os.writeBytes(monTexte);
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
