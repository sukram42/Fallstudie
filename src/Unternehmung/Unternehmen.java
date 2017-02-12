package Unternehmung;

import Exceptions.SpielendeException;
import Exceptions.ZuWenigMitarbeiterException;
import Rules.Game;
import Unternehmung.Abteilungen.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Unternehmen {

    private Calendar gruendungsDatum;
    private Calendar gameEnd;

    private String passwort;
    private String name;
    private transient Kennzahlensammlung kennzahlensammlung;

    private transient Map<String, Abteilung> abteilungen = new HashMap<String, Abteilung>();

    public Unternehmen(String name, String passwort, float eigenkapital) {
        super();
        this.gruendungsDatum = (Calendar) Game.getCalendar().clone();
        this.gameEnd = (Calendar) this.gruendungsDatum.clone();
        this.gameEnd.add(Calendar.YEAR, 10); // Spielende nach 10 Jahren
        this.passwort = passwort;
        this.name = name;
        this.kennzahlensammlung = new Kennzahlensammlung(this, eigenkapital);
        this.kennzahlensammlung.initWeicheKennzahlen(); // ausgelagert, um Zugriffe auf Kennzahlensammlung noch während diese erstellt wird zu vermeiden
        initDepartments();
    }

    /**
     * Methode zum Initialisieren der Abteilungen.
     */
    private void initDepartments() {
        abteilungen.put("marketing", new Marketing(kennzahlensammlung));
        abteilungen.put("finanzen", new Finanzen(this));
        abteilungen.put("produktion", new Produktion(kennzahlensammlung));
        abteilungen.put("forschung", new Forschung(kennzahlensammlung, abteilungen.get("produktion")));
        abteilungen.put("vertrieb", new Vertrieb(this, kennzahlensammlung, abteilungen.get("produktion")));
        abteilungen.put("hr", new HR(this, kennzahlensammlung));
        //Hinzufügen eines Vertriebsangestellten für den ersten Vertrag
        try {
            abteilungen.get("vertrieb").addMitarbeiter(1, 2000 );

        }catch (ZuWenigMitarbeiterException e){
            e.printStackTrace();
        }
    }

    public void update() {
        for (String key : abteilungen.keySet()) {
            abteilungen.get(key).update();
        }
        kennzahlensammlung.update();

        // falls 10 Jahre vergangen sind Abschlussbilanz aufstellen und Unternehmen in die Highscoreliste schreiben und aus Unternehmensliste entfernen:
        if (Game.getCalendar().equals(this.gameEnd)){
            this.kennzahlensammlung.getGuv().jahresabschluss(this.kennzahlensammlung.getBilanz());
            this.kennzahlensammlung.archivieren();
            Game.getHighscores().put(this.kennzahlensammlung.getBilanz().getEigenkapital(), this.name);
            Game.getCompanies().remove(this);
            // Exception, um am Frontend eine entsprechende Nachricht auszugeben:
            try {
                throw new SpielendeException();
            } catch (SpielendeException e){
                e.printStackTrace();
            }
        }
    }

    public void updateYearly() {
        this.kennzahlensammlung.getGuv().jahresabschluss(this.kennzahlensammlung.getBilanz());
        this.kennzahlensammlung.archivieren();
    }

    // Getter und Setter:
    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Abteilung getAbteilung(String key) {
        return abteilungen.get(key);
    }

    public Map<String, Abteilung> getAbteilungen() {
        return abteilungen;
    }

    public Kennzahlensammlung getKennzahlensammlung() {
        return kennzahlensammlung;
    }

    public Calendar getGruendungsDatum() {
        return gruendungsDatum;
    }

    public Calendar getGameEnd() {
        return gameEnd;
    }

}
