import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class ServeurThreads implements Runnable{

    private Socket clientSocket;
    
    public ServeurThreads(Socket socket) {
        clientSocket = socket;
    }
    
    @Override
    public void run() {
        try {
            PrintStream sortie = new PrintStream(
                    clientSocket.getOutputStream());
            BufferedReader entree = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            while (true) {
                String in = entree.readLine();
                if (in == null)
                    break;
                // Pour le moment, on répète seulement ce qu'on a lu,
                // donc :
                sortie.println(in);
            }
        } catch (IOException exception) {
            System.out.println("Erreur: " + exception);
        }
        
    }

}
