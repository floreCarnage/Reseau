package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import com.google.gson.Gson;
import protocole.Requete;

public class ClientTCP {

	private Socket maSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner mySc;
    private Gson requeteEnvoi;
    private Requete r;

    public static void main(String[] args) {
        new ClientTCP();
    }

    //Constructeur de la classe
    public ClientTCP() {
        try {
            //Connexion à notre serveur, en local
            maSocket = new Socket("localHost", 4444);

            System.out.println("Je suis connecté en local au serveur, maintenant je vais ouvrir les output et input");
            //Ouverture d'un output pour le client sur la socket de communication
            out = new PrintWriter(maSocket.getOutputStream(), true);
            //Ouvrir un reader pour le client sur la socket de communication
            in = new BufferedReader(new InputStreamReader(maSocket.getInputStream()));
            //On utiliser ce buffer pour récupérer ce qu'on dit sur le terminal (System.in)
            mySc = new Scanner(System.in);
            System.out.println("In et out ouverts");
            envoiRequetes();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    //Méthode pour envoyer des messages au serveur
    public void envoiRequetes() {
        requeteEnvoi = new Gson();
        String userInput;
        String cc = "coucou";
        try {
            System.out.println("Que voulez-vous faire ?\n" +
                    ">>>> 1 Ajouter une personne\n" +
                    ">>>> 2 Consulter les surnoms d'une personne\n" +
                    ">>");
            while (true) {
                userInput = mySc.nextLine();
                if (userInput.equals("1")) {
                    out.println(r.requeteAjout());
                }
                else if (userInput.equals("2")) {
                    out.println(r.requeteAjout());

                }

                //On envoie cette ligne immédiatement au serveur, avec le canal out de la socket
                //Ici, on lit l'entrée qui vient de la socket, donc on reçoit quelque chose ssi le serveur a renvoyé des informations
                System.out.println("echo: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
