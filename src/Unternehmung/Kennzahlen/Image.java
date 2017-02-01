package Unternehmung.Kennzahlen;

import Unternehmung.Unternehmen;

/**
 * Image setzt sich zusammen aus Mitarbeiterzufriedenheit und Kundenzufriedenheit
 * ist Grundlage zur Generierung von Preisen (sprich zur Durchsetzbarkeit von Preisen)
 * Created by boebel on 17.01.2017.
 */
public class Image extends Kennzahl{


    public Image(Unternehmen unternehmen) {
        super(unternehmen);
        this.berechnen();
        this.setWert(this.getWert());
    }
    @Override
    public float berechnen() {
        float mitarbeiterZufriedenheit = unternehmen.getKennzahlensammlung().getWeicheKennzahl("mitarbeiterzufriedenheit").getWert();
        float kundenZufriedenheit = unternehmen.getKennzahlensammlung().getWeicheKennzahl("kundenzufriedenheit").getWert();

        setBasiswert(mitarbeiterZufriedenheit * 0.5f + kundenZufriedenheit * 0.25f);
        return super.berechnen();
    }
}
