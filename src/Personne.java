import java.util.List;

/**
 * Created by Garance on 18/11/2014.
 */
public class Personne {
    String nom;
    String prenom;
    String apogee;
    Qualite qualite;
    Dpt departement;
    List<String> surnoms;

    public Personne(String sonNom, String sonPrenom, String sonApogee, String saQualite, String sonDpt) throws PersonneInvalideException {
        if (sonNom.isEmpty() || sonPrenom.isEmpty() || sonApogee.isEmpty() || saQualite.isEmpty() || sonDpt.isEmpty())
            throw new PersonneInvalideException("Personne créée invalide");
        nom = sonNom;
        prenom = sonPrenom;
        apogee = sonApogee;
        qualite = Qualite.getQualite(saQualite);
        departement = Dpt.getDepartement(sonDpt);
    }

    public String getNom() {
        return nom;
    }

    public String getApogee() {
        return apogee;
    }
}
