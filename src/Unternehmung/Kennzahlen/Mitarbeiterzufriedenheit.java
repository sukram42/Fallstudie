package Unternehmung.Kennzahlen;

import Unternehmung.Abteilungen.HR;
import Unternehmung.Unternehmen;

/**
 * Created by boebel on 11.01.2017.
 */
public class Mitarbeiterzufriedenheit extends Kennzahl{

    public final float FESTGEL_GEHALT= 6000; // WERT PRO MONAT

    public Mitarbeiterzufriedenheit(Unternehmen unternehmen)
    {
        super(unternehmen);
    }

    @Override
    public float berechnen() {
        setBasiswert(((HR)unternehmen.getAbteilung("hr")).getDurchschnittlichesGehalt() / FESTGEL_GEHALT);
        return super.berechnen();
    }

}
