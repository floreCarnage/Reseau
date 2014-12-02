package client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import protocole.NomRequete;
import protocole.Requete;

public class ClientTCP {

	private Socket maSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner mySc;
    private Gson gson = new Gson();

    public static void main(String[] args) {
        new ClientTCP();
    }

    //Constructeur de la classe
    public ClientTCP() {
        try {
            //Connexion à notre serveur, en local
            maSocket = new Socket("localHost", 4444);

            System.err.println("Connexion au serveur réussie");
            //Ouverture d'un output pour le client sur la socket de communication
            out = new PrintWriter(maSocket.getOutputStream(), true);
            //Ouvrir un reader pour le client sur la socket de communication
            in = new BufferedReader(new InputStreamReader(maSocket.getInputStream()));
            //On utiliser ce buffer pour récupérer ce qu'on dit sur le terminal (System.in)
            mySc = new Scanner(System.in);
            System.err.println("Flux de données ouverts");
            envoiRequetes();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    //Méthode pour envoyer des messages au serveur
    public void envoiRequetes() {
        String requeteEnvoyee = "";
        String userInput = "";
        Requete r;
        List<String> params;
        try {

            while (true) {
                System.out.println("Que voulez-vous faire ?\n" +
                        ">>>> 1 Ajouter une personne\n" +
                        ">>>> 2 Consulter les surnoms d'une personne\n" +
                        ">>");
                userInput = mySc.nextLine();
                if (userInput.equals("1")) {
                    params = ajoutNom();
                    r = new Requete(NomRequete.AJOUTERNOM, params);
                    requeteEnvoyee = gson.toJson(r);
                }
                else if (userInput.equals("2")) {
                    params = listerSurnoms();
                    r = new Requete(NomRequete.LISTERSURNOM, params);
                    requeteEnvoyee = gson.toJson(r);
                }
                //Envoi effectif de la requête après sa construction :
                out.println(requeteEnvoyee);

                //Lecture de la réponse

                afficherReponse(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void afficherReponse(String s) {
        List<String> reponse = gson.fromJson(s, List.class);
        System.out.println("Résultat : \n");
        for (int i = 0; i<reponse.size();i++) {
            System.out.println(reponse.get(i));
        }

    }

    private List<String> ajoutNom() {
        List<String> params  = new ArrayList<String>();
        System.out.println("Vous voulez ajouter une nouvelle personne à la base. ");
        System.out.println("Nom ?\n" +
                ">");
        params.add(mySc.nextLine());
        System.out.println("Prénom ?\n" +
                ">");
        params.add(mySc.nextLine());
        System.out.println("N° Apogee ?\n" +
                ">");
        params.add(mySc.nextLine());
        System.out.println("Qualité (ETU1, 2, 3, 4, 5 ou PROF) ?\n" +
                ">");
        params.add(mySc.nextLine());
        System.out.println("Département (SI, MAM, ELEC, GE, BAT, GB ou PEIP) ?\n" +
                ">");
        params.add(mySc.nextLine());
        return params;
    }

    private List<String> listerSurnoms() {
        List<String> params  = new ArrayList<String>();
        System.out.println("Vous voulez voir tous les surnoms d'une personne ");
        System.out.println("Nom ?\n" +
                ">");
        params.add(mySc.nextLine());
        System.out.println("N° Apogee ?\n" +
                ">");
        params.add(mySc.nextLine());
        return params;
    }

}
