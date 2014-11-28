package donnees;

/**
 * Created by Garance on 18/11/2014.
 */
public enum Qualite {
    ETU1, ETU2, ETU3, ETU4, ETU5, PROF;


    public static Qualite getQualite(String laQualite) {
        for (Qualite q : values()) {
            if (laQualite.toUpperCase().equals(q.name())) {
                return q;
            }
        }
        return null;
    }
}
