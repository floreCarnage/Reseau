package protocole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Requete {
    
    private NomRequete nomRequete;
    private List<String> parametre;
    
    public Requete(NomRequete nr, List<String> para) {
        nomRequete = nr;
        parametre = para;
    }
    
    public void executer() {
        switch (nomRequete) {
        case AJOUTERNOM : 
            executerAjouterNom();
        }
    }

    private void executerAjouterNom() {
        
    }
    

}
