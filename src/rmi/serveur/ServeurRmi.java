package rmi.serveur;

import rmi.protocole.ProtocoleRmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Garance on 12/12/2014.
 */
public class ServeurRmi extends UnicastRemoteObject implements ProtocoleRmi {

        public static final String MESSAGE = "Hello world";

        public ServeurRmi() throws RemoteException {
            super(0);    // required to avoid the 'mic' step, see below
        }

        //Méthode du protocole RMI implémentée
        public String getMessage() throws RemoteException {
            return MESSAGE;
        }

        public static void main(String args[]) throws Exception {
            System.out.println("Serveur RMI démarré");

            try { //Création d'un registre RMI
                LocateRegistry.createRegistry(1099);
                System.out.println("java rmi créé dans le registre.");
            } catch (RemoteException e) {
                //Rien à faire, tout va bien
                System.out.println("le java rmi existe déjà dans le registre.");
            }

           //On instancie le serveur
            ServeurRmi obj = new ServeurRmi();

            // On lie notre serveur à un nom spécifique
            Naming.rebind("//localhost/ServeurRmi", obj);
            System.out.println("Serveur lié dans le registre");
        }
}
