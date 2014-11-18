/**
 * Created by Garance on 18/11/2014.
 */
public enum Dpt {
    ELEC, SI, MAM, GE, BAT, GB, PEIP;

    public static Dpt getDepartement(String leDpt) {
        for (Dpt d : values()) {
            if (leDpt.toUpperCase().equals(d.name())) {
                return d;
            }
        }
        return null;
    }
}
