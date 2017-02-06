package Unternehmung.Abteilungen;


import Exceptions.ZuWenigMitarbeiterException;
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
        super("Human-Resources", kennzahlensammlung);
        this.unternehmen = unternehmen;
    }

    /**
     * Gibt Monatsgehalt aller Beschäftigten zurück
     *
     * @return
     */
    public float getTotalGehalt() {
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
        if (anzahl == 0) return -1;
        return (gehalt / anzahl);
    }

    public int getTotalMitarbeiterCount() {
        int count = 0;
        for (Abteilung abteilung : unternehmen.getAbteilungen().values()) {
            count += abteilung.getMitarbeiterAnzahl();
        }
        return count;
    }

    public ArrayList<Mitarbeiter> getTotalMitarbeiter() {
        ArrayList<Mitarbeiter> erg = new ArrayList<>();
        for (Abteilung abteilung : unternehmen.getAbteilungen().values()) {
            erg.addAll(abteilung.getMitarbeiter());
        }
        return erg;
    }

    public boolean kuendigeMitarbeiter(Mitarbeiter opfer) throws ZuWenigMitarbeiterException {
        for (Abteilung a : unternehmen.getAbteilungen().values()) {
            if (a.getMitarbeiter().contains(opfer)) {
                // Falls Mitarbeiter in HR eingestellt war können weniger neue Leute eingestellt werden, falls er nicht in HR war kann nun wieder einer mehr eingestellt werden
                if (opfer.getDepartment().getName().equals("Human-Resources")) {
                    // es kann nur ein HR-Mitarbeiter entlassen werden, wenn genügend weitere HR-Mitarbeiter vorhanden sind
                    if (this.kennzahlensammlung.getMaxNeueMitarbeiter() - 9 >= 0) {
                        this.kennzahlensammlung.setMaxNeueMitarbeiter(this.kennzahlensammlung.getMaxNeueMitarbeiter() - 9); // ein HR-Mitabreiter ist für 10 Leute zuständig
                    } else {
                        throw new ZuWenigMitarbeiterException("Human-Resources"); // zu wenig HR-Manager
                    }
                } else if (a.getName().equals("Marketing")) {
                    Marketing marketing = (Marketing) a;
                    if (marketing.getVerfuegbareMitarbeiter() - 1 >= 0) {
                        this.kennzahlensammlung.setMaxNeueMitarbeiter(this.kennzahlensammlung.getMaxNeueMitarbeiter() + 1);
                    } else {
                        throw new ZuWenigMitarbeiterException("Marketing");
                    }
                } else if (a.getName().equals("Finanzen")){
                    Finanzen finanzen = (Finanzen) a;
                    if (finanzen.getKredite().size() > 0 && finanzen.getMitarbeiter().size() - 1 > 0){
                        this.kennzahlensammlung.setMaxNeueMitarbeiter(this.kennzahlensammlung.getMaxNeueMitarbeiter() + 1);
                    } else {
                        throw new ZuWenigMitarbeiterException("Finanzen");
                    }
                } else if (a.getName().equals("Vertrieb")){
                    Vertrieb vertrieb = (Vertrieb) a;
                    if (vertrieb.getAccounts().size() >= vertrieb.getMitarbeiter().size()){
                        this.kennzahlensammlung.setMaxNeueMitarbeiter(this.kennzahlensammlung.getMaxNeueMitarbeiter() + 1);
                    } else {
                        throw new ZuWenigMitarbeiterException("Vertrieb");
                    }
                } else {
                    this.kennzahlensammlung.setMaxNeueMitarbeiter(this.kennzahlensammlung.getMaxNeueMitarbeiter() + 1);
                }
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
