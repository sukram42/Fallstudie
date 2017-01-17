package Unternehmung.Kennzahlen;

import Unternehmung.Unternehmen;

/**
 * Created by boebel on 17.01.2017.
 */
public class Kundenzufriedenheit extends Kennzahl {

    public Kundenzufriedenheit(Unternehmen unternehmen) {
        super(unternehmen);
    }
    @Override
    public float berechnen() {
        float kundenzufriedenheit = unternehmen.getKennzahlensammlung().getWeicheKennzahl("Kundenzufriedenheit").getWert();
        float image = unternehmen.getKennzahlensammlung().getWeicheKennzahl("image").getWert();

        setBasiswert(kundenzufriedenheit * 0.1f + image * 0.3f);
        return super.berechnen();
    }
}
