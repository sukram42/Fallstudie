package Unternehmung.Abteilungen;


import Exceptions.ZuWenigMitarbeiterException;
import Unternehmung.Abteilung;
import Unternehmung.Objekte.SozialProjekt;
import Unternehmung.Objekte.ZeitGeld;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Mitarbeiter;
import Unternehmung.Unternehmen;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Abteilung Human Resources
 */
public class HR extends Abteilung {
    private transient Unternehmen unternehmen;
    private List<SozialProjekt> projekte = new ArrayList<>();

    /**
     * Konstruktor, zum Erstellen der Abteilung Human Resources
     *
     * @param kennzahlensammlung Kennzahlenobjekt wird später benötigt, um Kennzahlensammlung laufend fortzuschreiben / zu berechnen
     */
    public HR(Unternehmen unternehmen, Kennzahlensammlung kennzahlensammlung) {
        super("Human-Resources", kennzahlensammlung);
        this.unternehmen = unternehmen;
        initProjects();
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
                } else if (a.getName().equals("Finanzen")) {
                    Finanzen finanzen = (Finanzen) a;
                    if (finanzen.getKredite().size() > 0 && finanzen.getMitarbeiter().size() - 1 > 0) {
                        this.kennzahlensammlung.setMaxNeueMitarbeiter(this.kennzahlensammlung.getMaxNeueMitarbeiter() + 1);
                    } else {
                        throw new ZuWenigMitarbeiterException("Finanzen");
                    }
                } else if (a.getName().equals("Vertrieb")) {
                    Vertrieb vertrieb = (Vertrieb) a;
                    if (vertrieb.getAccounts().size() >= vertrieb.getMitarbeiter().size()) {
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


    public void startProjekt(String name) {
        getProjektByName(name).start();
    }

    public void stopProjekt(String name) {
        getProjektByName(name).stop();
    }

    public void changeProjectActivity(String name) {
        SozialProjekt sp = getProjektByName(name);
        if (sp.isActive()) {
            stopProjekt(name);
        } else {
            startProjekt(name);
        }
    }

    /**
     * Bei jedem countersignal werden die AktuellenKosten berechnet.
     * Wenn ein Projekt aktiv ist so wird deren Aktuelle Kosten drauf gerechnet
     */
    @Override
    public void update() {
        for (SozialProjekt p : projekte) {
            aktKosten += p.isActive() ? p.getAktKosten() : 0;
            p.update();
            //Aktuelle Kosten wieder resetten
            p.resetAktKosten();
        }
    }

    public void initProjects() {
        projekte.add(new SozialProjekt("kantine", 100000, 5000, 0.3f, unternehmen));
        projekte.add(new SozialProjekt("wifi", 10000, 500, 0.1f, unternehmen));
        projekte.add(new ZeitGeld("urlaubsgeld", (this.getTotalGehalt() * 0.5f), 0.2f, Calendar.JULY, unternehmen));
        projekte.add(new ZeitGeld("weihnachtsgeld", (this.getTotalGehalt() * 0.3f), 0.15f, Calendar.DECEMBER, unternehmen));
        projekte.add(new SozialProjekt("kindergarten", 100000, 2000, 0.25f, unternehmen));
    }

    // Hilfsmethoden + Getter und Setter:
    private SozialProjekt getProjektByName(String name) {
        if (name != null) {
            return projekte.stream().filter(s -> name.equals(s.getName()))
                    .findFirst().get();
        } else return null;
    }

    /**
     * @return Monatsgehalt aller Beschäftigten zurück
     */
    public float getTotalGehalt() {
        float gehaelter = 0;
        for (Abteilung abteilung : unternehmen.getAbteilungen().values()) {
            gehaelter += abteilung.getMitarbeiterKosten();
        }
        return gehaelter;
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

    public List<SozialProjekt> getProjects() {
        return projekte;
    }
}
