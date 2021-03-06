package donnees;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Garance on 18/11/2014.
 */
public class Donnees {
    List<Personne> donnees;
    List<String> surnoms;

    public Donnees () {
        donnees = new ArrayList();
    }

    public void ajouterPersonne(Personne qn) {
        donnees.add(qn);
    }

    public Personne getPersonne(String nom, String apogee) throws PersonneInvalideException {
        for (Personne p : donnees) {
            if (p.getNom().equals(nom) && p.getApogee().equals(apogee)) {
                return p;
            }
        }
        throw new PersonneInvalideException("La personne demandée n'existe pas !");
    }
    
    public List<Personne> getDonnees() {
        return donnees;
    }
}
