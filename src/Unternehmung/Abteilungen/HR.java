package Unternehmung.Abteilungen;


import Unternehmung.Abteilung;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Mitarbeiter;
import Unternehmung.Unternehmen;

import java.util.ArrayList;

/**
 * Abteilung Human Resources
 */
public class HR extends Abteilung {
    private transient Unternehmen unternehmen;

    /**
     * Konstruktor, zum Erstellen der Abteilung Human Resources
     *
     * @param kennzahlensammlung Kennzahlenobjekt wird später benötigt, um Kennzahlensammlung laufend fortzuschreiben / zu berechnen
     */
    public HR(Unternehmen unternehmen, Kennzahlensammlung kennzahlensammlung) {
        super("Human-Resources" ,kennzahlensammlung);
        this.unternehmen = unternehmen;
    }

    public float getTotalGehalt()
    {
        float gehalt = 0;
        for (Abteilung abteilung : unternehmen.getAbteilungen().values()) {
            for (Mitarbeiter arbeiter : abteilung.getMitarbeiter()) {
                gehalt += (float) arbeiter.getGehalt() / 12;
            }
        }
        return gehalt;
    }
    public float getDurchschnittlichesGehalt() {
        float anzahl = getTotalMitarbeiterCount();
        float gehalt = getTotalGehalt();
        if(anzahl==0)return -1;
        return (gehalt / anzahl);
    }
    public int getTotalMitarbeiterCount()
    {
        int count = 0;
        for (Abteilung abteilung : unternehmen.getAbteilungen().values()) {
            count+=abteilung.getMitarbeiterAnzahl();
        }
        return count;
    }
    public ArrayList<Mitarbeiter> getTotalMitarbeiter()
    {
        ArrayList<Mitarbeiter> erg = new ArrayList<>();
        for (Abteilung abteilung : unternehmen.getAbteilungen().values()) {
            erg.addAll(abteilung.getMitarbeiter());
        }
        return erg;
    }

    public boolean kuendigeMitarbeiter(Mitarbeiter opfer)
    {
        for(Abteilung a : unternehmen.getAbteilungen().values())
        {
            if(a.getMitarbeiter().contains(opfer))
            {
                a.getMitarbeiter().remove(opfer);
                return true;
            }
        }
        return false;
    }

    @Override
    public void update() {

    }
}
