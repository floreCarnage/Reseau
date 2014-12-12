package protocole;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import donnees.Donnees;
import donnees.Personne;
import donnees.PersonneInvalideException;


public class Requete {
    
    private NomRequete nomRequete;
    private List<String> parametre;
    
    public Requete(NomRequete nr, List<String> para) {
        nomRequete = nr;
        parametre = para;
    }
    
    public String executer(Donnees donnee) throws Exception {
        switch (nomRequete) {
        case AJOUTERNOM : 
            try {
                return executerAjouterNom(donnee);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        case LISTERSURNOM :
            try {
                return executerListerSurnom(donnee);
            } catch (Exception e) {
                e.printStackTrace();
            }
        case DECONNEXION :
            try {
                return deconnexion();
            } catch (Exception e) {
                e.printStackTrace();
            }
        default:
            throw new Exception();
        }
        return null;
    }

    private String deconnexion() {
        return "deconnexion";
    }

    private String executerListerSurnom(Donnees donnee) throws PersonneInvalideException {
        int index = donnee.getDonnees().indexOf(donnee.getPersonne(parametre.get(0), parametre.get(1)));
        Gson gson = new Gson();
        return gson.toJson(donnee.getDonnees().get(index).getSurnom(), List.class);
    }

    private String executerAjouterNom(Donnees donnee) throws PersonneInvalideException {
        Personne p = new Personne(parametre.get(0), parametre.get(1), parametre.get(2), parametre.get(3), parametre.get(4));  
        if(donnee.getDonnees().contains(p)) {
            throw new PersonneInvalideException("Cette personne existe déjà");
        }
        else {
            donnee.getDonnees().add(p);
            String s = "Cette personne a bien été ajouté";
            ArrayList<String> l = new ArrayList<>();
            l.add(s);
            Gson gson = new Gson();
            return gson.toJson(l, List.class);
        }
    }
    

}
