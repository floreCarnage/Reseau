package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.Socket;

import com.google.gson.Gson;

import protocole.Requete;

//import com.google.gson.Gson;

public class ServeurThreads  extends ServeurTCP implements Runnable{

    private Socket clientSocket;
    private Inet4Address adresse;
    
    public ServeurThreads(Socket socket) {
    System.out.println("Je suis dans le constructeur de thread");
        clientSocket = socket;
        System.out.println("J'ai attribué la socket");
        adresse = (Inet4Address) socket.getInetAddress();
        System.out.println("J4ai attribué l'adresse");
    }
    
    @Override
    public void run() {
        try {
            PrintStream sortie = new PrintStream(
                    clientSocket.getOutputStream());
            BufferedReader entree = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            System.err.println("Le client "+adresse+" s'est connecté");
            String s="";
            //while ( !s.equals("deconnexion")) {
                Gson gson = new Gson();
                String in = entree.readLine();
                try {
                    Requete r = gson.fromJson(in, Requete.class);
                    try {
                        s = r.executer(getDonnees());
                        sortie.println(s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    System.err.println("Requête incompréhensible !");
                    sortie.println("Requête incompréhensible !");
                }


                /*    System.err.println("Le client " + adresse + " s'est déconnecté");
                    sortie.close();
                    entree.close();
                    clientSocket.close();
                    break;
                */
            //}
            sortie.close();
            entree.close();
            clientSocket.close();
            
        } catch (IOException exception) {
            System.out.println("Erreur: " + exception);
        }
        
    }

}
