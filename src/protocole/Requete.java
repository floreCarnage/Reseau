package protocole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import donnees.Donnees;


public class Requete {
    
    private NomRequete nomRequete;
    private List<String> parametre;
    
    public Requete(NomRequete nr, List<String> para) {
        nomRequete = nr;
        parametre = para;
    }
    
    public void executer(Donnees donnee) {
        switch (nomRequete) {
        case AJOUTERNOM : 
            executerAjouterNom(donnee);
        }
    }

    private void executerAjouterNom(Donnees donnee) {
        if(donnee.)
    }
    

}
