package Unternehmung.Abteilungen;

import Exceptions.BankruptException;
import Exceptions.LaufzeitZuHochException;
import Exceptions.ZuHochVerschuldetException;
import Exceptions.ZuWenigMitarbeiterException;
import Rules.Game;
import Unternehmung.Abteilung;
import Unternehmung.Objekte.Kredit;
import Unternehmung.Unternehmen;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Klasse, die die Abteilung Finanzen repräsentiert
 * Created by lucadommes on 29.12.2016.
 */
public class Finanzen extends Abteilung {

    ArrayList<Kredit> kredite = new ArrayList<Kredit>();
    private transient Unternehmen unternehmen;

    /**
     * Konstruktor, zum Erstellen der Abteilung
     * @param unternehmen für Zugriff auf Kennzahlensammlung und Spielende
     */
    public Finanzen(Unternehmen unternehmen) {
        super("Finanzen", unternehmen.getKennzahlensammlung());
        this.unternehmen = unternehmen;
    }

    /**
     * berechnet den Zinssatz auf Basis des neuen Verschuldungsgrades (bis 200% möglich) und erstellt ein Kreditobjekt, falls Kreditwürdigkeit gegeben ist
     * zur Kreditaufnahme ist ein Mitarbeiter in der Abteilung Finanzen notwendig
     * @param hoehe vom Spieler gewählt
     * @param laufzeit vom Spieler gewählt
     */
    public void kreditAufnehmen(int hoehe, int laufzeit) throws ZuHochVerschuldetException, ZuWenigMitarbeiterException, BankruptException, LaufzeitZuHochException {
        if (this.mitarbeiter.size() > 0) {
            Calendar faelligkeitsTag = (Calendar) Game.getCalendar().clone();
            faelligkeitsTag.add(Calendar.DAY_OF_MONTH, laufzeit);
            if (faelligkeitsTag.before(this.unternehmen.getGameEnd())) {
                double neuerVerschuldungsgrad = (kennzahlensammlung.getBilanz().getFremdkapital() + hoehe) /
                        kennzahlensammlung.getBilanz().getEigenkapital();
                double zinssatz = 0;
                if (neuerVerschuldungsgrad <= 0.25) {
                    zinssatz = 0.02;
                } else if (neuerVerschuldungsgrad <= 0.5) {
                    zinssatz = 0.025;
                } else if (neuerVerschuldungsgrad <= 0.75) {
                    zinssatz = 0.03;
                } else if (neuerVerschuldungsgrad <= 1) {
                    zinssatz = 0.035;
                } else if (neuerVerschuldungsgrad <= 1.25) {
                    zinssatz = 0.04;
                } else if (neuerVerschuldungsgrad <= 1.5) {
                    zinssatz = 0.045;
                } else if (neuerVerschuldungsgrad <= 1.75) {
                    zinssatz = 0.05;
                } else if (neuerVerschuldungsgrad <= 2) {
                    zinssatz = 0.06;
                } else {
                    throw new ZuHochVerschuldetException();
                }
                Kredit kredit = new Kredit(hoehe, laufzeit, zinssatz);
                this.kennzahlensammlung.getBilanz().fremdkapitalAnpassen(hoehe);
                this.kennzahlensammlung.getBilanz().liquiditaetAnpassen(hoehe);
                kredite.add(kredit);
            } else {
                throw new LaufzeitZuHochException();
            }
        } else {
            throw new ZuWenigMitarbeiterException("Finanzen");
        }
    }

    /**
     * rechnet für jeden Kredit einmal jährlich Zinsen und Tilgung ab
     */
    @Override
    public void update(){
        double tilgung = 0;
        double zinsen = 0;
        for (Kredit kredit : kredite){
            if (Game.getCalendar().get(Calendar.MONTH) == kredit.getBeginn().get(Calendar.MONTH) &&
                    Game.getCalendar().get(Calendar.DAY_OF_MONTH) == kredit.getBeginn().get(Calendar.DAY_OF_MONTH) &&
                    Game.getCalendar().get(Calendar.YEAR) > kredit.getBeginn().get(Calendar.YEAR)){ // einmal im Jahr Zinsen und Tilgung zahlen
                kredit.updateYearly();
                tilgung += kredit.getTilgung() * 12;
                zinsen += kredit.getZinsen();
            }
            if (!Game.getCalendar().equals(kredit.getBeginn()) &&
                    Game.getCalendar().get(Calendar.DAY_OF_MONTH) == kredit.getBeginn().get(Calendar.DAY_OF_MONTH)) {
                kredit.setLaufzeit(kredit.getLaufzeit() - 1);
            }
            // bei Laufzeitende löschen des Kredits und ggf. Berechnung der anteiligen Zinsen eines angebrochenen Jahres
            if (kredit.getLaufzeit() == 0) {
                int angebrochenesJahr = kredit.getBeginn().get(Calendar.MONTH) - Game.getCalendar().get(Calendar.MONTH); // = Anzahl Monate für die Zinsen gezahlt werden müssen
                if (angebrochenesJahr < 0){
                    angebrochenesJahr = 12 + angebrochenesJahr;
                }
                zinsen += (float) (kredit.getZinsen() / 12) * angebrochenesJahr;
                tilgung += kredit.getTilgung() * angebrochenesJahr;
                kredite.remove(kredit);
            }
        }
        try {
            // Zinsen an GuV weitergeben:
            this.kennzahlensammlung.getGuv().addZinsaufwendungen((float) zinsen);
            // liquide Mittel und FK anpassen:
            this.kennzahlensammlung.getBilanz().liquiditaetAnpassen((float) - (tilgung + zinsen));
            this.kennzahlensammlung.getBilanz().fremdkapitalAnpassen(- (float) tilgung);
        } catch (BankruptException e){
            e.printStackTrace();
        }
    }

    // Getter and Setter:
    public ArrayList<Kredit> getKredite() {
        return kredite;
    }

}
