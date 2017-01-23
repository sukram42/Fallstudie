package Unternehmung.Kennzahlen;

import Unternehmung.Unternehmen;

/**
 * Created by boebel on 17.01.2017.
 */
public class Kundenzufriedenheit extends Kennzahl {

    public Kundenzufriedenheit(Unternehmen unternehmen) {
        super(unternehmen);
        setBasiswert(0.1f);
    }
    @Override
    public float berechnen() {
        float mitarbeiterzufriedenheit = unternehmen.getKennzahlensammlung().getWeicheKennzahl("mitarbeiterzufriedenheit").getWert();
        float image = unternehmen.getKennzahlensammlung().getWeicheKennzahl("image").getWert();

        setBasiswert(mitarbeiterzufriedenheit * 0.1f + image * 0.3f);
        return super.berechnen();
    }
}
